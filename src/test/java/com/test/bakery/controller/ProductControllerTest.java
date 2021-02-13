package com.test.bakery.controller;

import com.test.bakery.MyTestRequestFactory;
import com.test.bakery.model.Product;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.Before;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    Product product;

    @Test
    @Order(1)
    void getProductByName() throws Exception {
        String name = "bread2";
        mockMvc.perform(MyTestRequestFactory.myFactoryRequest("http://localhost:8080/api/index/" + name)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.productName").value("bread2"))
                .andExpect(jsonPath("$.productId").value("3"))
                .andExpect(jsonPath("$.description").value("descr3"))
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
                .andExpect(jsonPath("$[0].productId").value(2))
                .andExpect(jsonPath("$[1].productId").value(3))
                .andExpect(jsonPath("$[2].productId").value(1))
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
    void updateOtp() {
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