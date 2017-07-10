package com.monitise.alkin.service;

import com.monitise.alkin.model.LoginRequest;
import com.monitise.alkin.model.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
