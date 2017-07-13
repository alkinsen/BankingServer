package com.monitise.alkin.model;

import com.monitise.alkin.data.entity.Account;

import java.util.List;

public class AccountListResponse extends BaseResponse{

    private List<Account> accountList;
    private long userId;

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
