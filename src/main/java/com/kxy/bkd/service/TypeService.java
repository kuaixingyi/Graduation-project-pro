package com.kxy.bkd.service;

import com.kxy.bkd.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

//定义增，删，改，查接口,分页查询接口方法
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeName(String name);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id,Type type);

    void deleteType(Long id);

}
