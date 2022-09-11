package com.example.security.service;

import com.example.security.data.dto.SignInResultDto;
import com.example.security.data.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto signUp(String id, String password, String name, String role);
    SignInResultDto signIn(String id, String password) throws RuntimeException;
}
