package com.monitise.alkin.service;

import com.monitise.alkin.core.MessageUtil;
import com.monitise.alkin.data.entity.User;
import com.monitise.alkin.data.repository.UserRepository;
import com.monitise.alkin.model.LoginRequest;
import com.monitise.alkin.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String customerNo = loginRequest.getCustomerNo();
        String password = loginRequest.getPassword();

        LoginResponse response = new LoginResponse();

        //customerNo is not 7 digits long
        if(!customerNo.matches("^[0-9]{7}$")){
            response.setMessage(messageUtil.getMessage("err.msg.invalid.customerNo"));
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            return response;
        }

        User user = userRepository.findByCustomerNo(loginRequest.getCustomerNo());

        if(user != null && user.getPassword().equals(password) ){
            response.setStatusCode(HttpStatus.OK);
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setId(user.getId());
        }else{
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.setMessage(messageUtil.getMessage("err.msg.incorrect.credentials"));
        }

        return response;
    }
}
