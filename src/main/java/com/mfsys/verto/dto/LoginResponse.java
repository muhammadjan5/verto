package com.mfsys.verto.dto;

import com.mfsys.verto.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private UserModel user;}
