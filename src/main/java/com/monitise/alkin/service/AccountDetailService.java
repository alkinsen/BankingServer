package com.monitise.alkin.service;

import com.monitise.alkin.model.AccountDetailResponse;

public interface AccountDetailService {
    AccountDetailResponse getAccountDetails(long accountId);
}
