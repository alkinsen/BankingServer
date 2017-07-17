package com.monitise.alkin.service;

import com.monitise.alkin.data.repository.AccountRepository;
import com.monitise.alkin.model.AccountDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailServiceImpl implements AccountDetailService {

    @Autowired
    AccountRepository accountRepository;

    public AccountDetailResponse getAccountDetails(long accountId) {

        AccountDetailResponse accountDetailResponse = new AccountDetailResponse();

        accountDetailResponse.setAccount(accountRepository.findOne(accountId));

        return accountDetailResponse;
    }
}
