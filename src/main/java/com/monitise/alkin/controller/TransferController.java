package com.monitise.alkin.controller;

import com.monitise.alkin.core.IbanUtil;
import com.monitise.alkin.core.MessageUtil;
import com.monitise.alkin.core.Constants;
import com.monitise.alkin.exceptions.BadRequestException;
import com.monitise.alkin.model.TransferRequest;
import com.monitise.alkin.model.TransferResponse;
import com.monitise.alkin.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TransferController {

    private static final Logger log = Logger.getLogger(TransferController.class.getName());


    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private TransferService transferService;

    @RequestMapping(value = "/transfer", method = POST)
    public TransferResponse transfer(HttpServletRequest httpServletRequest,
                                     @RequestBody TransferRequest transferRequest) {

        if (!IbanUtil.isIbanValid(transferRequest.getFromIBAN()) || !IbanUtil.isIbanValid(transferRequest.getToIBAN())) {
            log.info("Transfer Unsuccessful. Amount:" +transferRequest.getAmount() +
                    " fromIBAN: " + transferRequest.getFromIBAN() + " toIBAN:" + transferRequest.getToIBAN());
            throw new BadRequestException(messageUtil.getMessage("err.msg.incorrect.iban"));
        }

        return transferService.transfer(transferRequest,
                (long) httpServletRequest.getSession().getAttribute(Constants.USER_ID));
    }

}
