package com.study.essentialguide.service;

import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {
    @Override
    public String getHello() {
        return "Hello";
    }
}
