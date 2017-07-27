package com.monitise.alkin.service;

import com.monitise.alkin.MonitiseInternsAlkinApplication;
import com.monitise.alkin.data.entity.User;
import com.monitise.alkin.data.repository.UserRepository;
import com.monitise.alkin.exceptions.BadRequestException;
import com.monitise.alkin.exceptions.UnauthorizedAccessException;
import com.monitise.alkin.model.LoginRequest;
import com.monitise.alkin.model.LoginResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitiseInternsAlkinApplication.class)
public class LoginServiceImplTest {

    @Autowired
    private LoginService loginService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User alkin = new User();
        alkin.setCustomerNo("1234567");
        alkin.setPassword("qwerty");
        alkin.setFirstName("alkin");
        alkin.setLastName("sen");
        alkin.setId(1L);
        alkin.setAge(22);

        Mockito.when(userRepository.findByCustomerNo("1234567"))
                .thenReturn(alkin);
    }
    @Test(expected = BadRequestException.class)
    public void invalidCustomerNoFormatTest() {

        loginService.login(new LoginRequest("123r4567","qwerty"));

    }

    @Test(expected = UnauthorizedAccessException.class)
    public void invalidCustomerNoOrPasswordTest() {

        loginService.login(new LoginRequest("1234567","qwerty1"));

    }


    @Test
    public void validLoginRequestTest() {

        LoginResponse found = loginService.login(new LoginRequest("1234567","qwerty"));

        assertThat(found.getFirstName())
                .isEqualTo("alkin");
    }
}
