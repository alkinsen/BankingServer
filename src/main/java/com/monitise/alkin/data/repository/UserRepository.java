package com.monitise.alkin.data.repository;

import com.monitise.alkin.data.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByCustomerNo(String customerNo);
}
