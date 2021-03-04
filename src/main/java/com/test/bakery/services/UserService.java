package com.test.bakery.services;

import com.test.bakery.exceptions.ResourceNotFoundException;
import com.test.bakery.exceptions.UserAlreadyExistsException;
import com.test.bakery.mail_cfg.SendEmailService;
import com.test.bakery.model.Role;
import com.test.bakery.model.Userr;
import com.test.bakery.model.VerificationToken;
import com.test.bakery.repository.RoleRepository;
import com.test.bakery.repository.UserrRepository;
import com.test.bakery.repository.VerificationTokenRepository;
import com.test.bakery.security_controller.RegisterResponse;
import com.test.bakery.security_controller.RegistrationRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class UserService {

    private final UserrRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final SendEmailService sendEmailService;

    public UserService(UserrRepository userEntityRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, VerificationTokenRepository verificationTokenRepository, SendEmailService sendEmailService) {
        this.userRepository = userEntityRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepository = verificationTokenRepository;
        this.sendEmailService = sendEmailService;
    }

    public void registerUser(RegistrationRequest registrationRequest, String role) {
        boolean userrFromDB = userRepository.findByLogin(registrationRequest.getLogin()).isPresent();
        if (userrFromDB)
            throw new UserAlreadyExistsException("User with login: " + registrationRequest.getLogin() + " already exists");
        Userr userr = new Userr(registrationRequest.getUserName(),
                registrationRequest.getUserLastName(),
                registrationRequest.getLogin(),
                passwordEncoder.encode(registrationRequest.getPassword()),
                null, registrationRequest.getEmail(), false);
        Role userRole;
        if (role.equals("user")) {
            userRole = roleRepository.findByRoleName("ROLE_USER");
        } else {
            userRole = roleRepository.findByRoleName("ROLE_ADMIN");
        }
        userr.setRole(userRole);
        userRepository.save(userr);
        try {
            sendEmailService.sendEmail(registrationRequest.getEmail(), "Registration confirmation");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public List<Userr> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public RegisterResponse confirmUserrAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("VerificationToken with token " + token + " not found"));
        Userr userr = verificationToken.getUser();
        userr.setEnabled(true);
        userRepository.save(userr);
        return new RegisterResponse("Account confirmation complete!");
    }

    public Userr findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException("User with login " + login + " not found"));
    }

    public Userr findByLoginAndPassword(String login, String password) {
        Userr userr = findByLogin(login);
        if (userr != null && passwordEncoder.matches(password, userr.getPassword())) {
            return userr;
        }
        return null;
    }

}
