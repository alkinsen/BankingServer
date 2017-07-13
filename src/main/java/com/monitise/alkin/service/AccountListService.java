package com.monitise.alkin.service;

import com.monitise.alkin.model.AccountListResponse;

public interface AccountListService {
    AccountListResponse getAccounts(long userId);
}
