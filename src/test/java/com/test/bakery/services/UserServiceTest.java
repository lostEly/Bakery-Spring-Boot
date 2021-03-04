package com.test.bakery.services;

import com.test.bakery.exceptions.ResourceNotFoundException;
import com.test.bakery.mail_cfg.SendEmailService;
import com.test.bakery.model.Userr;
import com.test.bakery.model.VerificationToken;
import com.test.bakery.repository.UserrRepository;
import com.test.bakery.repository.VerificationTokenRepository;
import com.test.bakery.security_controller.RegisterResponse;
import com.test.bakery.security_controller.RegistrationRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

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
        assertSame(RegisterResponse.class, userService.confirmUserrAccount(token).getClass());
        assertNotNull(vt.get().getUser());
        assertTrue(vt.get().getUser().getEnabled());
        Mockito.verify(verificationTokenRepository, Mockito.times(1)).findByToken(anyString());
        Mockito.verify(userrRepository, Mockito.times(1)).save(ArgumentMatchers.any(Userr.class));
    }

    @Test
    void completing_WrongTokenGiven_completing_WrongTokenGiven_ShouldReturnResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> userService.confirmUserrAccount(null));
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
}