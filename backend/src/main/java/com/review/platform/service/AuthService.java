package com.review.platform.service;

import com.review.platform.dto.LoginRequest;
import com.review.platform.dto.RegisterRequest;
import com.review.platform.dto.LoginResponse;
import com.review.platform.entity.User;
import com.review.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User register(RegisterRequest request) {
        // 检查用户是否已存在
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("用户名已存在");
        }
        
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("邮箱已存在");
        }
        
        // 创建新用户
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        
        return userRepository.save(user);
    }
    
    public LoginResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return new LoginResponse("token", "Bearer", user.getId(), user.getUsername(), user.getEmail(), user.getNickname());
            }
        }
        throw new RuntimeException("用户名或密码错误");
    }
    
    public User getCurrentUser() {
        // 这里应该从SecurityContext获取当前用户
        // 暂时返回null，需要配置Spring Security
        return null;
    }
    
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
} 