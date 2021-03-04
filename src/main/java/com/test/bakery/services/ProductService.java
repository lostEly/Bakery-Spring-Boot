package com.test.bakery.services;

import com.test.bakery.DTO.AddProductDTO;
import com.test.bakery.model.*;
import com.test.bakery.repository.CategoryRepository;
import com.test.bakery.repository.OrderRepository;
import com.test.bakery.repository.OrderToProductRepository;
import com.test.bakery.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.test.bakery.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class ProductService {
    Logger logger = LoggerFactory.getLogger(ProductService.class);
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
    public Order findOrderByOrderId(Long orderId)
    {
        return orderRepository.findOrderByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with orderId " + orderId));
    }

    public Product getProductByProductId(Long productId) {
        return productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with productId " + productId));
    }

    public Product getByProductName(String name) {
        return productRepository.findByProductName(name).orElseThrow(() ->
                new ResourceNotFoundException("Product with name " + name));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public OrderToProduct getByOrderOrderIdAndProductProductId(Long orderId, Long productId) {
        return orderToProductRepository.findByOrder_OrderIdAndProduct_ProductId(orderId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Otp with orderId " + orderId + " & productId "
                        + productId));
    }

    public void deleteOtp(OrderToProduct otp) {
        orderToProductRepository.delete(otp);
    }

    public Long getOrderInfo(String login) {
        Userr userr = userService.findByLogin(login);
        List<Order> orders = orderRepository.findAllByUserr_UserrId(userr.getUserrId());
        if (orders.isEmpty()) {
            Order newOrder = new Order();
            newOrder.setUserr(userr);
            orderRepository.save(newOrder);
            return newOrder.getOrderId();
        } else {
            Order order = orders.get(orders.size() - 1); // if last order !new -> create new
            if (!order.getStatus().getStatusName().equals("New")) { // if last orders is new -> continue it
                Order newOrder = new Order();
                newOrder.setUserr(userr);
                orderRepository.save(newOrder);
                return newOrder.getOrderId();
            } else return order.getOrderId();
        }
    }

    /**
     * to add prod in a basket need to get order info to use unpaid or create new order
     * having info create Otp(that is actually basket)
     * @return amount of products in a basket
     */
    public int addProductToBasket(Long orderId, Long productId, int amount) {
        Order order = this.findOrderByOrderId(orderId);
        Product product = this.getProductByProductId(productId);
        OrderToProduct otp = new OrderToProduct(amount, 0d, order, product);
        orderToProductRepository.save(otp);
        return orderToProductRepository.findAllByOrderOrderId(orderId).size();
    }

    public Order updateOTP(List<Product> products, Long orderId, double totalPrice) {
        OrderToProduct otp;
        for (Product product : products) {
            otp = orderToProductRepository.findByOrder_OrderIdAndProduct_ProductId(orderId, product.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("OTP with orderId " + orderId));
            otp.setAmount(product.getCount());
            otp.setCost(product.getCount() * product.getPrice());
            orderToProductRepository.save(otp);
        }
        Order order = this.findOrderByOrderId(orderId);
        order.setTotalPrice(totalPrice);
        Status status = new Status();
        status.setStatusId(3L);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Product editProduct(AddProductDTO product, Long productId) {
        Product product1 = this.getProductByProductId(productId);
        product1.setProductName(product.getProductName());
        product1.setPrice(product.getPrice());
        product1.setCount(1);
        product1.setDescription(product.getDescription());
        product1.setImage(product.getImage());
        product1.setCategory(categoryRepository.findByCategoryName(product.getCategory()));
        return productRepository.save(product1);

    }

    public Product addProduct(AddProductDTO productDTO) {
        Product product = new Product(
                productDTO.getProductName(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                1, productDTO.getImage(),
                categoryRepository.findByCategoryName(productDTO.getCategory())
        );
        return productRepository.save(product);

    }
}
