package com.mfsys.verto.service;

import com.mfsys.verto.dto.LoginRequest;
import com.mfsys.verto.dto.LoginResponse;
import com.mfsys.verto.model.UserModel;
import com.mfsys.verto.dto.SignupRequest;
import com.mfsys.verto.repository.UserRepository;
import com.mfsys.verto.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // UPDATE
    public UserModel update(Long id, UserModel user) {
        UserModel existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existing.setEmail(user.getEmail());
        if (user.getPassword() != null) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setDisplayName(user.getDisplayName());
        existing.setAvatarUrl(user.getAvatarUrl());
        existing.setJobTitle(user.getJobTitle());
        existing.setLocation(user.getLocation());
        existing.setBio(user.getBio());
        existing.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(existing);
    }

    // SIGNUP
    public UserModel signup(SignupRequest request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> { throw new RuntimeException("Email already exists"); });

        UserModel user = new UserModel();
        user.setEmail(request.getEmail());
       user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        return userRepository.save(user);
    }

    // LOGIN
    public LoginResponse login(LoginRequest request) {

        UserModel user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = JwtUtil.generateToken(user.getEmail());
        return new LoginResponse(token);
    }
}
