package code.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import code.model.pojo.Role;
import code.model.pojo.User;
import code.services.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

//import com.kb.service.CustomUserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = userService.findUserByLogin(username);

        if (user == null || !user.getLogin().equalsIgnoreCase(username) || user.getEnabled()!=1) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!(BCrypt.checkpw(password,user.getPassword()))) {
            throw new BadCredentialsException("Wrong password.");
        }

        Role r = new Role();
        r.setName(user.getRole());
        List<Role> roles = new ArrayList<Role>();
        roles.add(r);

        return new UsernamePasswordAuthenticationToken(user, password,roles);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

}