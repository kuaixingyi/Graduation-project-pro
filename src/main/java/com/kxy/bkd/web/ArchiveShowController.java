package com.kxy.bkd.web;

import com.kxy.bkd.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：快兴毅
 * @date ：Created in 2020/12/28 10:32
 * @description：
 * @modified By：
 * @version: $
 */

@Controller
public class ArchiveShowController {


    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){

        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());

        return "archives";

    }
}
