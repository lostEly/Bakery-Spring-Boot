package com.test.bakery.controller;


import com.test.bakery.DTO.AddProductDTO;
import com.test.bakery.excel_cfg.ExcelUserExporter;
import com.test.bakery.model.Product;
import com.test.bakery.model.Userr;
import com.test.bakery.services.ProductService;
import com.test.bakery.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/")
@CrossOrigin()
public class AdminController {
    private final ProductService productService;
    private final UserService userService;

    public AdminController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("edit-product/{productId}")
    public Product getEditProduct(@PathVariable Long productId) {
        return productService.getProductByProductId(productId);
    }

    @PutMapping("edit-product/{productId}")
    public Product putEditProduct(@RequestBody AddProductDTO product, @PathVariable Long productId){
        return productService.editProduct(product, productId);
    }

    @PostMapping("add-product")
    public Product addProduct(@RequestBody AddProductDTO product){
        return productService.addProduct(product);
    }

    @GetMapping("users/export/excel")
    public void exportUsersToExcel(HttpServletResponse response) throws IOException {
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
