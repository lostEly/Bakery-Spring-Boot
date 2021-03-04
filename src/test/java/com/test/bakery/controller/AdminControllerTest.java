package com.test.bakery.controller;

import com.test.bakery.MyTestRequestFactory;
import com.test.bakery.repository.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-order-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-product-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-otp-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-otp-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/create-product-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/create-order-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminControllerTest {

    Logger logger = LoggerFactory.getLogger(AdminControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void getChangeProduct() throws Exception {
        long productId = 1L;
        mockMvc.perform(MyTestRequestFactory.myFactoryRequest("http://localhost:8080/admin/edit-product/" + productId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.productName").value("product_name"))
                .andReturn();
    }

    @Test
    void addProduct() throws Exception {
        mockMvc.perform(MyTestRequestFactory.myFactoryPostRequest("http://localhost:8080/admin/add-product")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"description\" : \"description3\", \"price\" : \"555\", \"productName\" : \"product_name3\", \"category\" : \"Bread\",\n" +
                " \"image\" : \"https://www.lanworks.com/wp-content/uploads/2017/02/test-button-1024x1024.png\", \"count\" : \"1\"}"))
        .andExpect(status().isOk());
    }

    @Test
    void editProduct() throws Exception {
        long productId = 1L;
        mockMvc.perform(MyTestRequestFactory.myFactoryPutRequest("http://localhost:8080/admin/edit-product/" + productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"description\" : \"description_changed\", \"price\" : \"525\", \"productName\" : \"product_name_changed\", \"category\" : \"Bread\",\n" +
                        " \"image\" : \"https://www.lanworks.com/wp-content/uploads/2017/02/test-button-1024x1024.png\"}"))
                .andExpect(status().isOk());
    }

}