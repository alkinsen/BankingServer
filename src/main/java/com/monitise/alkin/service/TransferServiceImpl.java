package com.monitise.alkin.service;

import com.monitise.alkin.core.MessageUtil;
import com.monitise.alkin.data.entity.Account;
import com.monitise.alkin.data.repository.AccountRepository;
import com.monitise.alkin.model.TransferRequest;
import com.monitise.alkin.model.TransferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class TransferServiceImpl implements TransferService{

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public TransferResponse transfer(TransferRequest transferRequest, long userId) {
        TransferResponse transferResponse = new TransferResponse();

        Account fromAccount = accountRepository.findAccountByIban(transferRequest.getFromIBAN());
        Account toAccount = accountRepository.findAccountByIban(transferRequest.getToIBAN());
        int amount = Integer.parseInt(transferRequest.getAmount());

        if(fromAccount == null || toAccount == null ){
            transferResponse.setMessage(messageUtil.getMessage("err.msg.incorrect.iban"));
            transferResponse.setStatusCode(HttpStatus.BAD_REQUEST);
            return transferResponse;
        }

        if(userId != fromAccount.getUser().getId()){
            transferResponse.setStatusCode(HttpStatus.NOT_FOUND);
            transferResponse.setMessage(messageUtil.getMessage("err.msg.page.not.found"));
            return transferResponse;
        }

        if(fromAccount.getBalance() < amount || fromAccount.getAccountLimit() < amount){
            transferResponse.setMessage(messageUtil.getMessage("err.msg.not.enough.funds"));
            transferResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        }else{

            fromAccount.setBalance(fromAccount.getBalance()-amount);
            toAccount.setBalance(toAccount.getBalance()+amount);

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            transferResponse.setNewBalance(fromAccount.getBalance());
            transferResponse.setStatusCode(HttpStatus.OK);
        }

        return transferResponse;
    }

}
