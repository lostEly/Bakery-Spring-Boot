package com.test.bakery.service;

import com.test.bakery.DTO.AddProductDTO;
import com.test.bakery.model.*;
import com.test.bakery.repository.CategoryRepository;
import com.test.bakery.repository.OrderRepository;
import com.test.bakery.repository.OrderToProductRepository;
import com.test.bakery.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.test.bakery.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Класс выполняющий функционал, касающийся продукции
 *
 * @author Stely
 * @version 1.0
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderToProductRepository orderToProductRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, OrderToProductRepository orderToProductRepository, OrderRepository orderRepository, UserService userService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.orderToProductRepository = orderToProductRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    public Product findByProductName(String name) {
        return productRepository.findByProductName(name).orElseThrow(() ->
                new ResourceNotFoundException("ProductNotFound with name " + name));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public OrderToProduct findByOrderOrderIdAndProductProductId(Long orderId, Long productId) {
        return orderToProductRepository.findByOrder_OrderIdAndProduct_ProductId(orderId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("OTP with orderId " + orderId + " & "
                        + productId + "not found"));
    }

    public void deleteOtp(OrderToProduct otp) {
        orderToProductRepository.delete(otp);
    }

    public Long getOrderInfo(String login) {
        Userr userr = userService.findByLogin(login);
        List<Order> orders = orderRepository.getAllByUserr_UserrId(userr.getUserrId());
        if (orders.isEmpty()) {
            Order newOrder = new Order();
            newOrder.setUserr(userr);
            orderRepository.save(newOrder);
            return newOrder.getOrderId();
        } else {
            Order order = orders.get(orders.size() - 1); // если последний заказ не новый -> создаем новый
            if (!order.getStatus().getStatusName().equals("New")) { // если последний заказ новый -> продолжаем его
                Order newOrder = new Order();
                newOrder.setUserr(userr);
                orderRepository.save(newOrder);
                return newOrder.getOrderId();
            } else return order.getOrderId();
        }
    }

    // для добавления товара в корзину получаем инфу по ордеру, что бы юзался либо не оплаченный, либо создался новый
    // имея инфу берем айди заказа, название товара, колво и создаем ОТП(корзину)
    // инфу по количеству товаров в корзине берем количеством ОТП для этого заказа.
    // возвращается колво товаров в корзине
    public int addProductToBasket(Long orderId, Long productId, int amount) {
        OrderToProduct otp = new OrderToProduct();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with orderId " + orderId + " not found"));
        otp.setOrder(order);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with productId " + productId + " not found"));
        otp.setProduct(product);
        otp.setAmount(amount);
        orderToProductRepository.save(otp);
        return orderToProductRepository.findAllByOrderOrderId(orderId).size();

    }

    public void updateOTP(List<Product> products, Long orderId, double totalPrice) {
        OrderToProduct otp;
        for (Product product : products) {
            otp = orderToProductRepository.findByOrder_OrderIdAndProduct_ProductId(orderId, product.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("OTP with orderId " + orderId + " not found"));
            otp.setAmount(product.getCount());
            otp.setCost(product.getCount() * product.getPrice());
            orderToProductRepository.save(otp);
        }
        Order order = orderRepository.getOrderByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("OTP with orderId " + orderId + " not found"));
        order.setTotalPrice(totalPrice);
        Status status = new Status();
        status.setStatusId(3L);
        order.setStatus(status);
        orderRepository.save(order);

//        OrderToProduct otp = orderToProductRepository.findByOrder_OrderIdAndProduct_ProductId(orderId, productId);
//        otp.setAmount(amount);
//        otp.setCost(cost);
//        orderToProductRepository.save(otp);
    }

    public void updateOrder(Long orderId, double totalPrice) {
        Order order = orderRepository.getOrderByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("OTP with orderId " + orderId + " not found"));
        order.setTotalPrice(totalPrice);
        Status status = new Status();
        status.setStatusName("Ongoing");
        order.setStatus(status);
        orderRepository.save(order);
    }

    public Product getProductByProductId(Long productId) {
        return productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with productId " + productId + " not found"));
    }

    public String changeProduct(AddProductDTO product) {
        Product product1 = productRepository.findByProductName(product.getProductName()).orElseThrow(() ->
                new ResourceNotFoundException("ProductNotFound with name" + product.getProductName()));
        product1.setProductName(product.getProductName());
        product1.setPrice(product.getPrice());
        product1.setCount(1);
        product1.setDescription(product.getDescription());
        product1.setImage(product.getImage());
        product1.setCategory(categoryRepository.findByCategoryName(product.getCategory()));
        productRepository.save(product1);
        return "OK";
    }

    public String deleteProduct(Long productId) {
        productRepository.delete(productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with productId " + productId + " not found")));
        return "OK";
    }

    public String addProduct(AddProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setPrice(productDTO.getPrice());
        product.setCategory(categoryRepository.findByCategoryName(productDTO.getCategory()));
        product.setCount(1);
        productRepository.save(product);
        return "OK";
    }
}
