package com.github.yjgbg.jpaplussample.controller;

import com.github.yjgbg.jpaplussample.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginCtl {
    @GetMapping("login")
    public void login(String username) {
        SecurityUtils.setCurrentUser(username);
    }
}
