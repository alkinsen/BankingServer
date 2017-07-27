package com.monitise.alkin.service;

import com.monitise.alkin.MonitiseInternsAlkinApplication;
import com.monitise.alkin.data.entity.Account;
import com.monitise.alkin.data.repository.AccountRepository;
import com.monitise.alkin.model.AccountDetailResponse;
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
public class AccountDetailServiceImplTest {

    @Autowired
    private AccountDetailService accountDetailService;

    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {

        Account checking = new Account();
        checking.setIban("GR1601101250000000012300695");
        checking.setBalance(1000);
        checking.setId(1234567);
        checking.setAccountLimit(10000);

        Mockito.when(accountRepository.findOne(1234567L))
                .thenReturn(checking);

    }

    @Test
    public void validAccountListRequestTest() {

        AccountDetailResponse accountDetailResponse = accountDetailService.getAccountDetails(1234567L);
        assertThat(accountDetailResponse.getAccount().getIban()).isEqualTo("GR1601101250000000012300695");

    }
}
