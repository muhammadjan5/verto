package com.mfsys.verto.controller;

import com.mfsys.verto.dto.LoginRequest;
import com.mfsys.verto.dto.LoginResponse;
import com.mfsys.verto.model.UserModel;
import com.mfsys.verto.dto.SignupRequest;
import com.mfsys.verto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // UPDATE
    @PutMapping("/{id}")
    public UserModel update(@PathVariable Long id, @RequestBody UserModel user) {
        return userService.update(id, user);
    }

    // SIGNUP
    @PostMapping("/signup")
    public UserModel signup(@RequestBody SignupRequest request) {
        return userService.signup(request);
    }

    // LOGIN
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
