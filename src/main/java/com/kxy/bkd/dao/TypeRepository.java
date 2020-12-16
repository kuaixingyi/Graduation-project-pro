package com.kxy.bkd.dao;

import com.kxy.bkd.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

//实现对数据库的操作
public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
