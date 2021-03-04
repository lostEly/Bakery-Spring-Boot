package com.test.bakery.controller;

import com.test.bakery.MyTestRequestFactory;
import com.test.bakery.model.Product;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.Before;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void getProductByName() throws Exception {
        String name = "product_name";
        mockMvc.perform(MyTestRequestFactory.myFactoryRequest("http://localhost:8080/api/index/" + name)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.productName").value("product_name"))
                .andExpect(jsonPath("$.productId").value("1"))
                .andExpect(jsonPath("$.description").value("description"))
                .andExpect(jsonPath("$.count").value("1"));
        System.out.println("getProductByName");
//
//        String name = "asd";
//        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/index/" + name );
//        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
//        assertEquals(HttpStatus.SC_NOT_FOUND,
//                httpResponse.getStatusLine().getStatusCode());
//
//        RequestEntity<?> req;
    }

    @Test
    @Order(2)
    void getAllProducts() throws Exception {
        mockMvc.perform(MyTestRequestFactory.myFactoryRequest("http://localhost:8080/api/index")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[1].productId").value(2))
                .andExpect(status().isOk());
        System.out.println("getAllProducts");
    }


    @Test
    @Order(3)
    void addProductToBasket() throws Exception {
        mockMvc.perform(MyTestRequestFactory.myFactoryPostRequest("http://localhost:8080/api/index")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"login\" : \"admin1\", \"productId\" : \"1\", \"amount\" : \"5\"}"))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println("addProductToBasket");
    }

    @Test
    @Order(4)
    void updateOtp() throws Exception {
        mockMvc.perform(MyTestRequestFactory.myFactoryPutRequest("http://localhost:8080/api/index/cart")
                .contentType(MediaType.APPLICATION_JSON)
        .content("{\"products\" : [ {\"productId\" : \"1\", \"description\" : \"description\", \"price\" : \"555\", \"productName\" : \"product_name\", \"categoryId\" : \"1\",\n" +
                " \"image\" : \"https://www.lanworks.com/wp-content/uploads/2017/02/test-button-1024x1024.png\", \"count\" : \"5\"} ]," +
                " \"login\" : \"admin1\"," +
                " \"totalPrice\" : \"99999\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value("99999.0"))
                .andReturn();
        System.out.println("updateOtp");
    }
    @Test
    @Order(5)
    void deleteOtp() throws Exception {

        mockMvc.perform(MyTestRequestFactory.myFactoryDeleteRequest("http://localhost:8080/api/index/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"login\" : \"admin1\", \"productId\" : \"1\"}"))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println("deleteOtp");
    }
}