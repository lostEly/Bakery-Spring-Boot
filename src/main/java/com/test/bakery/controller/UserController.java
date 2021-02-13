//package com.test.bakery.controller;
//
//import com.test.bakery.model.Userr;
//import com.test.bakery.repository.UserrRepository;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin("*")
//public class UserController {
//    private final UserrRepository userrRepository;
//
//    public UserController(UserrRepository userrRepository) {
//        this.userrRepository = userrRepository;
//    }
//
//    @GetMapping("/user/{login}")
//    public Userr getUser(@PathVariable (value = "login") String login )// String login
//    {
//        return userrRepository.findByLogin(login);
//    }
//
//}
