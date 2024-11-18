package com.demo2springsecurity.service;

import com.demo2springsecurity.entiy.UserInfo;
import com.demo2springsecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserInfoRepository userInfoRepository;

    public String addUser(UserInfo userInfo) {
        String encodedPassword = passwordEncoder.encode(userInfo.getPassword());
//       return userInfoRepository.save(username, encodedPassword);
        userInfo.setPassword(encodedPassword);
        userInfoRepository.save(userInfo);
        return "user added successfully";
    }

}
