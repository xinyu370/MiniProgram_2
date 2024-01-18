package com.mini.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    //@Autowired
    private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

   // private PasswordEncoder passwordEncoder = new MyPassWordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String passWord = authentication.getCredentials().toString();
        UserDetailsInfo userDetails = (UserDetailsInfo)userDetailServiceImpl.loadUserByUsername(userName);
        if(passwordEncoder.matches(passWord,userDetails.getPassword()) && "1".equals(userDetails.getEnabled())){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, passWord, userDetails.getAuthorities());
            //存放authentication到SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            return usernamePasswordAuthenticationToken;
        }else{
            throw new BadCredentialsException("用户名或密码错误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
