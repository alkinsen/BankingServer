package com.monitise.alkin.controller;

import com.monitise.alkin.core.MessageUtil;
import com.monitise.alkin.core.Constants;
import com.monitise.alkin.data.entity.Account;
import com.monitise.alkin.exceptions.ForbiddenAccessException;
import com.monitise.alkin.model.AccountDetailResponse;
import com.monitise.alkin.service.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
public class AccountDetailController {

    private static final Logger log = Logger.getLogger(AccountDetailController.class.getName());

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private AccountDetailService accountDetailService;

    @RequestMapping(value = "/account/detail")
    public AccountDetailResponse accountDetail(HttpServletRequest httpServletRequest,
                                               @RequestParam(Constants.ACCOUNT_ID) long accountId) {

        AccountDetailResponse accountDetailResponse = accountDetailService.getAccountDetails(accountId);
        Account account = accountDetailResponse.getAccount();

        long sessionUserId = (long) httpServletRequest.getSession().getAttribute(Constants.USER_ID);
        if (account == null || sessionUserId != account.getUser().getId()) {
            log.info("Get Account Detail Unsuccessful. " +
                    "LoggedUserId:" + sessionUserId + " RequestedAccountId:" + accountId);
            throw new ForbiddenAccessException(messageUtil.getMessage("err.msg.forbidden.access"));
        }

        log.info("Get Account Detail Successful. " +
                "LoggedUserId:" + sessionUserId + " RequestedAccountId:" + accountId);
        return accountDetailResponse;
    }

}