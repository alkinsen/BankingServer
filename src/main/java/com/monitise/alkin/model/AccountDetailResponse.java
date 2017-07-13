package com.monitise.alkin.model;

import com.monitise.alkin.data.entity.Account;

public class AccountDetailResponse extends BaseResponse{

    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
