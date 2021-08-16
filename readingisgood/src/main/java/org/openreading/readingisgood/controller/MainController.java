package org.openreading.readingisgood.controller;

import org.openreading.readingisgood.security.TokenService;
import org.openreading.readingisgood.security.User;
import org.openreading.readingisgood.security.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */

@RestController
public class MainController {

    TokenService tokenService;

    @Autowired
    public MainController(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public String getToken(@RequestHeader("username") String username,@RequestHeader("password") String password){
        return tokenService.generateToken(new User(1,username,password, UserType.ADMIN));

    }
}
