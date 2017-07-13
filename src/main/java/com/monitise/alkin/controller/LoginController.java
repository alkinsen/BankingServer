package com.monitise.alkin.controller;

import com.monitise.alkin.core.Constants;
import com.monitise.alkin.model.LoginRequest;
import com.monitise.alkin.model.LoginResponse;
import com.monitise.alkin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/login",method=POST)
    public ResponseEntity<LoginResponse> login(HttpServletRequest httpServletRequest,
                                               @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(loginRequest);

        if(loginResponse.getStatusCode() == HttpStatus.OK) {
            httpServletRequest.getSession().setAttribute(Constants.USER_ID, loginResponse.getId());
        }

        return new ResponseEntity<>(loginResponse, loginResponse.getStatusCode());
    }
}
