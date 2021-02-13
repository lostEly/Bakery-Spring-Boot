package com.test.bakery.controller;


import com.test.bakery.DTO.AddProductDTO;
import com.test.bakery.excel_cfg.ExcelUserExporter;
import com.test.bakery.exceptions.ResourceNotFoundException;
import com.test.bakery.model.Product;
import com.test.bakery.model.Userr;
import com.test.bakery.service.ProductService;
import com.test.bakery.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Класс управления панелью администратора
 * @author Stely
 * @version 1.0
 */

@RestController
@RequestMapping("/admin")
@CrossOrigin()
public class AdminController {
    private final ProductService productService;
    private final UserService userService;

    public AdminController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    /**
     * Функция получения всей продукции
     * @return возвращает список продуктов, хранящийся в БД
     */
    @GetMapping("/")
    public List<Product> getAllProducts () {
        return productService.findAll();
    }

    /**
     * Функция, получения информации о товаре, который необходимо изменить
     * @param productId - идентификатор товара
     * @return возвращает объект товара
     */
    @GetMapping("/edit-product/{productId}")
    public Product getChangeProduct(@PathVariable Long productId)
    {
       return productService.getProductByProductId(productId);
    }

    /**
     *
     * @param product
     * @return
     */
    @PostMapping("/add-product")
    public String addProduct(@RequestBody AddProductDTO product){
        return productService.addProduct(product);
    }

    /**
     *
     * @param product
     * @return
     */
    @PutMapping("/edit-product")
    public String changeProduct(@RequestBody AddProductDTO product){
        //передать сюда изменения, то есть тот же продукт
        return productService.changeProduct(product);
    }

    /**
     *
     * @param productId
     * @return
     */
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId)
    {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/users/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Userr> listUsers = userService.getAllUsers();

        ExcelUserExporter excelUserExporter = new ExcelUserExporter(listUsers);

        excelUserExporter.export(response);
    }
}
