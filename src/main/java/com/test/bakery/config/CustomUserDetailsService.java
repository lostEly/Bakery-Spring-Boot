package com.test.bakery.config;

import com.test.bakery.model.Userr;
import com.test.bakery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Userr userr = userService.findByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userr);
    }
}
