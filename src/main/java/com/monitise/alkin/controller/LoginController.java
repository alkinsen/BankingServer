package com.monitise.alkin.controller;

import com.monitise.alkin.model.LoginRequest;
import com.monitise.alkin.model.LoginResponse;
import com.monitise.alkin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/login",method=POST)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(loginRequest);
        return new ResponseEntity<LoginResponse>(loginResponse, loginResponse.getStatusCode());
    }
}