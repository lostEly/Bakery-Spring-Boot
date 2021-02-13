package com.test.bakery.service;

import com.test.bakery.exceptions.ResourceNotFoundException;
import com.test.bakery.mailcfg.SendEmailService;
import com.test.bakery.model.Userr;
import com.test.bakery.model.VerificationToken;
import com.test.bakery.repository.UserrRepository;
import com.test.bakery.repository.VerificationTokenRepository;
import com.test.bakery.security_controller.RegistrationRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.mail.MessagingException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserrRepository userrRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private VerificationTokenRepository verificationTokenRepository;

    @MockBean
    SendEmailService sendEmailService;

    @Test
    void saveUser_UserObjectGiven_ShouldReturnTrue() {
        Userr userr = new Userr();
        assertTrue(userService.saveUser(userr));
        assertEquals("ROLE_USER", userr.getRole().getRoleName());
        Mockito.verify(userrRepository, Mockito.times(1)).findByLogin(userr.getLogin());
        Mockito.verify(userrRepository, Mockito.times(1)).save(userr);
        Mockito.verify(passwordEncoder, Mockito.times(1))
                .encode(ArgumentMatchers.eq(userr.getPassword()));
    }

    @Test
    void saveUser_ExistingUserGiven_ShouldReturnFalse() {
        Userr userr = new Userr();
        userr.setLogin("John");
        Optional<Userr> us = Optional.of(new Userr());
        Mockito.doReturn(us)
                .when(userrRepository)
                .findByLogin("John");
        boolean isUserCreated = userService.saveUser(userr);
        assertFalse(isUserCreated);
        Mockito.verify(userrRepository, Mockito.times(1)).findByLogin(userr.getLogin());
        Mockito.verify(userrRepository, Mockito.times(0)).save(ArgumentMatchers.any(Userr.class));
        Mockito.verify(passwordEncoder, Mockito.times(0))
                .encode(ArgumentMatchers.eq(userr.getPassword()));
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        assertNotNull(userService.getAllUsers());
        Mockito.verify(userrRepository, Mockito.times(1)).getAllUsers();
    }

    @Test
    void completing_TokenGiven_ExpectedOK_Message() {
        String token = "";
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(new Userr());
        Optional<VerificationToken> vt = Optional.of(verificationToken);
        Mockito.doReturn(vt)
                .when(verificationTokenRepository)
                .findByToken(token);
        assertEquals("OK", userService.completing(token));
        assertNotNull(vt.get().getUser());
        assertTrue(vt.get().getUser().getEnabled());
        Mockito.verify(verificationTokenRepository, Mockito.times(1)).findByToken(anyString());
        Mockito.verify(userrRepository, Mockito.times(1)).save(ArgumentMatchers.any(Userr.class));
    }

    @Test
    void completing_WrongTokenGiven_completing_WrongTokenGiven_ShouldReturnResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> userService.completing(null));
    }

    @Test
    void findByLogin_LoginIsGiven_ShouldReturnUserr() {
        Userr userr = new Userr();
        userr.setLogin("test");
        Optional<Userr> us = Optional.of(new Userr());
        Mockito.doReturn(us)
                .when(userrRepository)
                .findByLogin("test");
        assertSame(Userr.class, userService.findByLogin("test").getClass());
    }

    @Test
    void findByLogin_WrongLoginIsGiven_ShouldReturnResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> userService.findByLogin(null));
    }

    @Test
    void findByLoginAndPassword_LoginAndPasswordGiven_ShouldReturnUserr() {
        Userr userr = new Userr();
        userr.setLogin("");
        userr.setPassword("");
        UserService userService2 = Mockito.spy(userService);
        Mockito.doReturn(userr)
                .when(userService2)
                .findByLogin(anyString());
        Mockito.doReturn(true)
                .when(passwordEncoder)
                .matches("", userr.getPassword());
        assertSame(Userr.class, userService2.findByLoginAndPassword("", "").getClass());
    }

    @Test
    void findByLoginAndPassword_WrongLoginAndPasswordGiven_ShouldReturnNull() {
        Userr userr = new Userr();
        UserService userService2 = Mockito.spy(userService);
        Mockito.doReturn(userr)
                .when(userService2)
                .findByLogin(anyString());
        assertNull(userService2.findByLoginAndPassword("", ""));
    }

    @Test
    void saveModer() {
    }

    @Test
    void saveAdmin() {
    }

    @Test
    void registerUser_RegistrationRequestGiven_ShouldRegisterUserr() throws MessagingException {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail("");
        registrationRequest.setLogin("");
        UserService userService2 = Mockito.spy(userService);
        Mockito.doReturn(true)
                .when(userService2)
                .saveUser(new Userr());
        userService.registerUser(registrationRequest);
        Mockito.verify(sendEmailService, Mockito.times(1)).sendEmail(anyString(), anyString());
    }
}
//    @Test
//    void registerUser_BadRegistrationRequestGiven_ShouldRegisterUserr() throws MessagingException {
//
//    }