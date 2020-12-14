package com.kxy.bkd.web.admin;

import com.kxy.bkd.po.User;
import com.kxy.bkd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

//控制器
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
//登录方法
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username,password);
        if (user != null){
//            不传密码到前端页面
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";

        }else {

            attributes.addFlashAttribute("message","密码和用户名不正确");

            return "redirect:/admin";
        }

    }
//    注销方法
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";

    }

}
