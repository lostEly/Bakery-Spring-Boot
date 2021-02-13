package com.test.bakery.service;

import com.test.bakery.exceptions.ResourceNotFoundException;
import com.test.bakery.mailcfg.SendEmailService;
import com.test.bakery.model.Role;
import com.test.bakery.model.Userr;
import com.test.bakery.model.VerificationToken;
import com.test.bakery.repository.RoleRepository;
import com.test.bakery.repository.UserrRepository;
import com.test.bakery.repository.VerificationTokenRepository;
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


    public boolean saveUser(Userr userr) {
        boolean userrFromDB = userRepository.findByLogin(userr.getLogin()).isPresent();
        if (userrFromDB) return false;
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        userr.setRole(userRole);
        userr.setPassword(passwordEncoder.encode(userr.getPassword()));
        userRepository.save(userr);
        return true;
    }

    public List<Userr> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public String completing(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("VerificationToken with token " + token + " not found"));
        Userr userr = verificationToken.getUser();
        userr.setEnabled(true);
        userRepository.save(userr);
        return "OK";
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

    public void saveModer(Userr userr) {
        Role userRole = roleRepository.findByRoleName("ROLE_MODERATOR");
        userr.setRole(userRole);
        userr.setPassword(passwordEncoder.encode(userr.getPassword()));
        userr.setEnabled(true);
        userRepository.save(userr);
    }

    public void saveAdmin(Userr userr) {
        Role userRole = roleRepository.findByRoleName("ROLE_ADMIN");
        userr.setRole(userRole);
        userr.setPassword(passwordEncoder.encode(userr.getPassword()));
        userr.setEnabled(true);
        userRepository.save(userr);
    }

    public void registerUser(RegistrationRequest registrationRequest) {
        Userr u = new Userr();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        u.setUserrName(registrationRequest.getUserName());
        u.setUserrLastName((registrationRequest.getUserLastName()));
        u.setEmail(registrationRequest.getEmail());
        if (saveUser(u)) {
            try {
                sendEmailService.sendEmail(registrationRequest.getEmail(), "Registration confirmation");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }


}
