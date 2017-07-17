package com.monitise.alkin.controller;

import com.monitise.alkin.core.MessageUtil;
import com.monitise.alkin.core.Constants;
import com.monitise.alkin.core.SessionCheck;
import com.monitise.alkin.exceptions.BadRequestException;
import com.monitise.alkin.exceptions.PageNotFoundException;
import com.monitise.alkin.model.TransferRequest;
import com.monitise.alkin.model.TransferResponse;
import com.monitise.alkin.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.math.BigInteger;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TransferController {


    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private TransferService transferService;

    @RequestMapping(value = "/transfer", method = POST)
    public TransferResponse transfer(HttpServletRequest httpServletRequest,
                                     @RequestBody TransferRequest transferRequest) {
        HttpSession httpSession = httpServletRequest.getSession();

        if (SessionCheck.check(httpSession)) {
            throw new PageNotFoundException(messageUtil.getMessage("err.msg.page.not.found"));
        }

        if (!isIbanValid(transferRequest.getFromIBAN()) || !isIbanValid(transferRequest.getToIBAN())) {
            throw new BadRequestException(messageUtil.getMessage("err.msg.incorrect.iban"));
        }

        return transferService.transfer(transferRequest, (long) httpSession.getAttribute(Constants.USER_ID));
    }

    private boolean isIbanValid(String iban) {
        //iban is 27 characters long
        if (iban == null || iban.length() != 27) return false;


        //The first characters are letters. They are translated into numbers
        char first = iban.charAt(0);
        char second = iban.charAt(1);

        if (!Character.isAlphabetic(first) || !Character.isAlphabetic(second)) {
            return false;
        }

        String firstNum = (first - 'A' + 10) + "";
        String secondNum = (second - 'A' + 10) + "";


        //The first four characters are moved to the end of the number.

        iban = iban.substring(4).concat(firstNum).concat(secondNum).concat(iban.substring(2, 4));

        //If the modulo 97 is 1, then the initial account number is
        // correct ΙΒΑΝ format; else this is not an IBAN account number.
        BigInteger ibanNum = new BigInteger(iban);
        BigInteger result = ibanNum.mod(new BigInteger("97"));

        return result.intValue() == 1;
    }

}
