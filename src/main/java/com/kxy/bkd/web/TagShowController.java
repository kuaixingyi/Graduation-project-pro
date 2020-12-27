package com.kxy.bkd.web;

import com.kxy.bkd.po.Tag;
import com.kxy.bkd.service.BlogService;

import com.kxy.bkd.service.TagsService;
import com.kxy.bkd.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ：快兴毅
 * @date ：Created in 2020/12/26 17:43
 * @description：
 * @modified By：
 * @version: $
 */
@Controller
public class TagShowController {


    @Autowired
    private TagsService tagsService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model){


        List<Tag> tags = tagsService.lisTagTop(10000);
        if (id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";

    }
}
