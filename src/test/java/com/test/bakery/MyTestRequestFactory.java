package com.test.bakery;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class MyTestRequestFactory {

    public static MockHttpServletRequestBuilder myFactoryRequest(String url) {
        return MockMvcRequestBuilders.get(url)
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE2MTYxMDQ4MDB9.YIkWx2xzo-xiSVZfyoOG0WrFORuLViCMkJv_ddrCr1w-FvgPC6m30ep92GK2dXkqB0I7eMCd9uDe5eQTQK7qFQ");
    }
    public static MockHttpServletRequestBuilder myFactoryPostRequest(String url) {
        return MockMvcRequestBuilders.post(url)
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE2MTYxMDQ4MDB9.YIkWx2xzo-xiSVZfyoOG0WrFORuLViCMkJv_ddrCr1w-FvgPC6m30ep92GK2dXkqB0I7eMCd9uDe5eQTQK7qFQ");
    }
    public static MockHttpServletRequestBuilder myFactoryDeleteRequest(String url) {
        return MockMvcRequestBuilders.delete(url)
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE2MTYxMDQ4MDB9.YIkWx2xzo-xiSVZfyoOG0WrFORuLViCMkJv_ddrCr1w-FvgPC6m30ep92GK2dXkqB0I7eMCd9uDe5eQTQK7qFQ");
    }

    public static MockHttpServletRequestBuilder myFactoryPutRequest(String url) {
        return MockMvcRequestBuilders.put(url)
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE2MTYxMDQ4MDB9.YIkWx2xzo-xiSVZfyoOG0WrFORuLViCMkJv_ddrCr1w-FvgPC6m30ep92GK2dXkqB0I7eMCd9uDe5eQTQK7qFQ");
    }
}