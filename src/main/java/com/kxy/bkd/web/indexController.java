package com.kxy.bkd.web;

import com.kxy.bkd.NotFiundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

@Controller
public class indexController {

    @GetMapping("/")
    public String index(){
//        int i = 9/0;
//        String bolg = null;
//        if(bolg == null){
//            throw  new NotFiundException("消息不存在");
//        }
        return "index.HTML";
    }

    @GetMapping("/blog")
    public String blog(){
//        int i = 9/0;
//        String bolg = null;
//        if(bolg == null){
//            throw  new NotFiundException("消息不存在");
//        }
        return "blog";
    }
}

