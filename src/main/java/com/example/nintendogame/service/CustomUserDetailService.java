package com.example.nintendogame.service;

import com.example.nintendogame.entity.CustomUserDetail;
import com.example.nintendogame.entity.User;
import com.example.nintendogame.reponsitory.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("không tìm thấy người dùng");
        }
        return new CustomUserDetail(user, userRepository);
    }
}
