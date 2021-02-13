package com.test.bakery;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class MyTestRequestFactory {

    public static MockHttpServletRequestBuilder myFactoryRequest(String url) {
        return MockMvcRequestBuilders.get(url)
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE2MTM3NzIwMDB9.pgzWPz33BRRG0MtOiiT217jS9j0jiuVXQB7KYPx6zzUB36QoZ2Qb7OhV2_BRJYbjz6XkaLB_lOK-Lek7zgLAiA");
    }
    public static MockHttpServletRequestBuilder myFactoryPostRequest(String url) {
        return MockMvcRequestBuilders.post(url)
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE2MTM3NzIwMDB9.pgzWPz33BRRG0MtOiiT217jS9j0jiuVXQB7KYPx6zzUB36QoZ2Qb7OhV2_BRJYbjz6XkaLB_lOK-Lek7zgLAiA");
    }
    public static MockHttpServletRequestBuilder myFactoryDeleteRequest(String url) {
        return MockMvcRequestBuilders.delete(url)
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE2MTM3NzIwMDB9.pgzWPz33BRRG0MtOiiT217jS9j0jiuVXQB7KYPx6zzUB36QoZ2Qb7OhV2_BRJYbjz6XkaLB_lOK-Lek7zgLAiA");
    }
}