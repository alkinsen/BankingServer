package com.monitise.alkin.controller;

import com.monitise.alkin.core.MessageUtil;
import com.monitise.alkin.core.Constants;
import com.monitise.alkin.core.SessionCheck;
import com.monitise.alkin.exceptions.PageNotFoundException;
import com.monitise.alkin.model.AccountListResponse;
import com.monitise.alkin.service.AccountListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
public class AccountListController {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private AccountListService accountListService;

    @RequestMapping(value = "/accounts")
    public AccountListResponse listAccounts(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();

        if (SessionCheck.check(httpSession)) {
            throw new PageNotFoundException(messageUtil.getMessage("err.msg.page.not.found"));
        }
        return accountListService.getAccounts((long) httpSession.getAttribute(Constants.USER_ID));
    }


}
