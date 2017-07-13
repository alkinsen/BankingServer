package com.monitise.alkin.service;

import com.monitise.alkin.model.TransferRequest;
import com.monitise.alkin.model.TransferResponse;

public interface TransferService {
    TransferResponse transfer(TransferRequest transferRequest, long userId);
}
