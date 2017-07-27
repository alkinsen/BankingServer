package com.monitise.alkin.service;

import com.monitise.alkin.MonitiseInternsAlkinApplication;
import com.monitise.alkin.data.entity.Account;
import com.monitise.alkin.data.entity.User;
import com.monitise.alkin.data.repository.AccountRepository;
import com.monitise.alkin.model.AccountListResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitiseInternsAlkinApplication.class)
public class AccountListServiceImplTest {

    @Autowired
    private AccountListService accountListService;

    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        User alkin = new User();
        alkin.setCustomerNo("1234567");
        alkin.setPassword("qwerty");
        alkin.setId(1L);

        Account checking = new Account();
        checking.setIban("GR1601101250000000012300695");
        checking.setBalance(1000);
        checking.setId(1234567);
        checking.setAccountLimit(10000);
        checking.setUser(alkin);

        List<Account> accounts = new ArrayList<>();
        accounts.add(checking);

        alkin.setAccounts(accounts);

        Mockito.when(accountRepository.findAccountsByUserId(1L))
                .thenReturn(accounts);

    }

    @Test
    public void validAccountListRequestTest() {

        AccountListResponse accountListResponse = accountListService.getAccounts(1L);
        assertThat(accountListResponse.getAccountList().get(0).getIban()).isEqualTo("GR1601101250000000012300695");

    }
}
