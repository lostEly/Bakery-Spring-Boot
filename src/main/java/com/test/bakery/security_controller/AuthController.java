package com.test.bakery.security_controller;

import com.test.bakery.config.jwt.JwtProvider;
import com.test.bakery.exceptions.ResourceNotFoundException;
import com.test.bakery.mailcfg.EmailCfg;
import com.test.bakery.mailcfg.Feedback;
import com.test.bakery.mailcfg.SendEmailService;
import com.test.bakery.model.Userr;
import com.test.bakery.model.VerificationToken;
import com.test.bakery.repository.VerificationTokenRepository;
import com.test.bakery.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class AuthController {
    private final UserService userService;
    private final EmailCfg emailCfg;
    private final JwtProvider jwtProvider;
    private final SendEmailService sendEmailService;
    private final VerificationTokenRepository verificationTokenRepository;

    public AuthController(UserService userService, EmailCfg emailCfg, JwtProvider jwtProvider, SendEmailService sendEmailService, VerificationTokenRepository verificationTokenRepository) {
        this.userService = userService;
        this.emailCfg = emailCfg;
        this.jwtProvider = jwtProvider;
        this.sendEmailService = sendEmailService;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @PostMapping("/register")
    public RegisterResponse registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        userService.registerUser(registrationRequest);

        return new RegisterResponse("Account confirmation message has been sent to your email");
    }

    @PostMapping("/auth")
    public Object auth(@RequestBody AuthRequest request) {
        Userr userr = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(userr.getEnabled().equals(false) || userr.getEnabled()==null)
        {
            return "You have not confirmed your account. Please check your email and try again.";
        }
        String token = jwtProvider.generateToken(userr.getLogin());
        return new AuthResponse(userr.getLogin(), token);
    }
    @PostMapping("/send/message")
    public void sendFeedback(@RequestBody Feedback feedback) throws MessagingException {

        sendEmailService.sendEmail(feedback.getTo(), feedback.getTopic());

    }

    @GetMapping("/registration-complete")
    public String completing(@RequestParam String token)
    {
        return userService.completing(token);
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

    @PostMapping("/register/admin")
    public String registerAdmin(@RequestBody @Valid RegistrationRequest registrationRequest) {
        Userr u = new Userr();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        u.setUserrName(registrationRequest.getUserName());
        u.setUserrLastName((registrationRequest.getUserLastName()));
        u.setEmail(registrationRequest.getEmail());
        userService.saveAdmin(u);
        return "OK, teper` vi admin";
    }
}
