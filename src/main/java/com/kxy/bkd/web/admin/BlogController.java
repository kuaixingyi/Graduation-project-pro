package com.kxy.bkd.web.admin;

import com.kxy.bkd.po.Blog;
import com.kxy.bkd.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/admins")
//    分页方式
    public String admins(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Blog blog, Model model){

        model.addAttribute("page",blogService.ListBlog(pageable,blog));
        return "admin/admins";

    }

}
