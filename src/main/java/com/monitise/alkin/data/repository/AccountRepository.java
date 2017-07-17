package com.monitise.alkin.data.repository;

import com.monitise.alkin.data.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAccountsByUserId(long userId);

    Account findAccountByIban(String iban);
}
