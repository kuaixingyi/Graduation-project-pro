package com.kxy.bkd.service;

import com.kxy.bkd.po.User;
//定义服务接口
public interface UserService {
    User checkUser(String username,String password);
}
