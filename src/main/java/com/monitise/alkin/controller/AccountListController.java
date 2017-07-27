package com.monitise.alkin.controller;

import com.monitise.alkin.core.Constants;
import com.monitise.alkin.model.AccountListResponse;
import com.monitise.alkin.service.AccountListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
public class AccountListController {

    private static final Logger log = Logger.getLogger(AccountListController.class.getName());

    @Autowired
    private AccountListService accountListService;

    @RequestMapping(value = "/accounts")
    public AccountListResponse listAccounts(HttpServletRequest httpServletRequest) {
        long userId = (long) httpServletRequest.getSession().getAttribute(Constants.USER_ID);

        log.info("Get Account Listing Successful. User Id:" + userId );
        return accountListService.getAccounts(userId);
    }


}
