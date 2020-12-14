package com.kxy.bkd.dao;

import com.kxy.bkd.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
//继承JPA到user
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username,String password);
}
