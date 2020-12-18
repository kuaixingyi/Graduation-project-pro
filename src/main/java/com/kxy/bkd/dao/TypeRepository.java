package com.kxy.bkd.dao;

import com.kxy.bkd.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//实现对数据库的操作
public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);

//    自定义查询
    @Query("select t from T_type t")
    List<Type> findTop(Pageable pageable);
}
