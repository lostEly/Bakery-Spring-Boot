package com.test.bakery.security_controller;

import com.test.bakery.config.jwt.JwtProvider;
import com.test.bakery.exceptions.AccountIsNotConfirmed;
import com.test.bakery.model.Userr;
import com.test.bakery.services.UserService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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
    public RegisterResponse registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        userService.registerUser(registrationRequest, "user");
        return new RegisterResponse("Account confirmation message has been sent to your email");
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        Userr userr = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(userr.getEnabled().equals(false)) throw new AccountIsNotConfirmed("You have not confirmed your account. Please check your email and try again.");
        String token = jwtProvider.generateToken(userr.getLogin());
        return new AuthResponse(userr.getLogin(), token);
    }

    @GetMapping("/registration-complete")
    public RegisterResponse completing(@RequestParam String token)
    {
        return userService.confirmUserrAccount(token);
    }

    @PostMapping("/register/admin")
    public RegisterResponse registerAdmin(@RequestBody @Valid RegistrationRequest registrationRequest) {
        userService.registerUser(registrationRequest, "admin");
        return new RegisterResponse("You have successfully become an administrator");
    }
}
