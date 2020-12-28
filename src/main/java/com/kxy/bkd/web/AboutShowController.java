package com.kxy.bkd.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：快兴毅
 * @date ：Created in 2020/12/28 17:50
 * @description：
 * @modified By：
 * @version: $
 */

@Controller
public class AboutShowController {


    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
