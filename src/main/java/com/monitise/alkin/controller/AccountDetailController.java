package com.monitise.alkin.controller;

import com.monitise.alkin.core.MessageUtil;
import com.monitise.alkin.core.Constants;
import com.monitise.alkin.core.SessionCheck;
import com.monitise.alkin.data.entity.Account;
import com.monitise.alkin.model.AccountDetailResponse;
import com.monitise.alkin.service.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AccountDetailController {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private AccountDetailService accountDetailService;

    @RequestMapping(value="/account/detail")
    public ResponseEntity<AccountDetailResponse> accountDetail(HttpServletRequest httpServletRequest,
                                                       @RequestParam(Constants.ACCOUNT_ID) long accountId) {
        HttpSession httpSession = httpServletRequest.getSession();
        AccountDetailResponse accountDetailResponse = new AccountDetailResponse();

        if(SessionCheck.check(httpSession)){
            accountDetailResponse.setStatusCode(HttpStatus.FORBIDDEN);
            accountDetailResponse.setMessage(messageUtil.getMessage("err.msg.forbidden.access"));
        }else{
            accountDetailResponse = accountDetailService.getAccountDetails(accountId);
            Account account = accountDetailResponse.getAccount();
            if(account == null || httpSession.getAttribute(Constants.USER_ID) != account.getUser().getId()){
                accountDetailResponse.setStatusCode(HttpStatus.FORBIDDEN);
                accountDetailResponse.setMessage(messageUtil.getMessage("err.msg.forbidden.access"));
                accountDetailResponse.setAccount(null);
            }
        }

        return new ResponseEntity<>(accountDetailResponse, accountDetailResponse.getStatusCode());
    }


}