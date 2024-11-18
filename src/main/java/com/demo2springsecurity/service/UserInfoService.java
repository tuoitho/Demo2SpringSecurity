package com.demo2springsecurity.service;


import com.demo2springsecurity.entiy.UserInfo;
import com.demo2springsecurity.repository.UserInfoRepository;
import com.demo2springsecurity.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRole().getName()) // Chỉ định role cho user
//                .build();
        return user.map(UserPrincipal::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}
