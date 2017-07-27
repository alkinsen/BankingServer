package com.monitise.alkin.service;

import com.monitise.alkin.MonitiseInternsAlkinApplication;
import com.monitise.alkin.data.entity.Account;
import com.monitise.alkin.data.entity.User;
import com.monitise.alkin.data.repository.AccountRepository;
import com.monitise.alkin.exceptions.BadRequestException;
import com.monitise.alkin.exceptions.PageNotFoundException;
import com.monitise.alkin.model.TransferRequest;
import com.monitise.alkin.model.TransferResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitiseInternsAlkinApplication.class)
public class TransferServiceImplTest {

    @Autowired
    private TransferService transferService;

    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        User alkin = new User();
        alkin.setCustomerNo("1234567");
        alkin.setPassword("qwerty");
        alkin.setId(1L);

        Account from = new Account();
        from.setIban("GR1601101250000000012300695");
        from.setBalance(1000);
        from.setId(1234567);
        from.setAccountLimit(10000);
        from.setUser(alkin);

        Account to = new Account();
        to.setIban("MR1300020001010000123456753");
        to.setBalance(10000);
        to.setId(1231231);
        to.setAccountLimit(10000);


        Mockito.when(accountRepository.findAccountByIban("GR1601101250000000012300695"))
                .thenReturn(from);

        Mockito.when(accountRepository.findAccountByIban("MR1300020001010000123456753"))
                .thenReturn(to);

    }

    @Test
    public void valiTransferRequestTest() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setFromIBAN("GR1601101250000000012300695");
        transferRequest.setToIBAN("MR1300020001010000123456753");
        transferRequest.setAmount("500");

        TransferResponse transferResponse = transferService.transfer(transferRequest, 1L);
        assertThat(transferResponse.getNewBalance()).isEqualTo(500);

    }


    @Test(expected = BadRequestException.class)
    public void accountNotFoundTest() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setAmount("500");

        transferService.transfer(transferRequest, 1L);

    }

    @Test(expected = BadRequestException.class)
    public void notEnoughBalanceTest() {

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setFromIBAN("GR1601101250000000012300695");
        transferRequest.setToIBAN("MR1300020001010000123456753");
        transferRequest.setAmount("1500");

        transferService.transfer(transferRequest, 1L);

    }

    @Test(expected = PageNotFoundException.class)
    public void invalidUserIdTest() {

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setFromIBAN("GR1601101250000000012300695");
        transferRequest.setToIBAN("MR1300020001010000123456753");
        transferRequest.setAmount("500");

        transferService.transfer(transferRequest, 2L);

    }


}
