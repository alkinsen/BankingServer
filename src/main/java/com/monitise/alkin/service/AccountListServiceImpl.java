package com.monitise.alkin.service;

import com.monitise.alkin.data.entity.Account;
import com.monitise.alkin.data.repository.AccountRepository;
import com.monitise.alkin.model.AccountListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountListServiceImpl implements AccountListService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountListResponse getAccounts(long userId) {
        List<Account> accountList = accountRepository.findAccountsByUserId(userId);

        AccountListResponse accountListResponse = new AccountListResponse();
        accountListResponse.setAccountList(accountList);
        accountListResponse.setUserId(userId);

        return accountListResponse;
    }
}
