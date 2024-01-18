package com.mini.oauth.config;


import com.mini.oauth.dao.entity.AuthoritiesEntity;
import com.mini.oauth.dao.entity.UserEntity;
import com.mini.oauth.dao.AuthoritiesRepository;
import com.mini.oauth.dao.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s = ()->new UsernameNotFoundException("username:"+username+"in invalid!");
        UserEntity user = userRepository.findByUsername(username).orElseThrow(s);
        List<AuthoritiesEntity> authorities = authoritiesRepository.findByUserId(user.getId());
        UserDetailsInfo userDetails = new UserDetailsInfo();
        BeanUtils.copyProperties(user,userDetails);
        userDetails.setAuthorities(authorities);
        return  userDetails;
    }
}
