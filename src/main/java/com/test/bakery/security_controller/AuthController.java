package com.test.bakery.security_controller;

import com.test.bakery.config.jwt.JwtProvider;
import com.test.bakery.model.Userr;
import com.test.bakery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class AuthController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public RegisterResponse registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        Userr u = new Userr();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        u.setUserrName(registrationRequest.getUserName());
        u.setUserrLastName((registrationRequest.getUserLastName()));
        u.setEmail(registrationRequest.getEmail());
        userService.saveUser(u);
        return new RegisterResponse("Registration complete!");
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        Userr userr = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userr.getLogin());
        return new AuthResponse(userr.getLogin(), token);
    }


    @PostMapping("/register/moderator")
    public String registerModerator(@RequestBody @Valid RegistrationRequest registrationRequest) {
        Userr u = new Userr();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        u.setUserrName(registrationRequest.getUserName());
        u.setUserrLastName((registrationRequest.getUserLastName()));
        u.setEmail(registrationRequest.getEmail());
        userService.saveModer(u);
        return "OK, teper` vi moder";
    }

}
