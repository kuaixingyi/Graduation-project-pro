package com.kxy.bkd.web.admin;


import com.kxy.bkd.po.Tag;
import com.kxy.bkd.po.Type;
import com.kxy.bkd.service.TagsService;
import com.kxy.bkd.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TgsController {

    @Autowired
    private TagsService tagsService;

//    分页查询方式
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){

        model.addAttribute("page",tagsService.listTags(pageable));

        return "admin/tags";



    }
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tags",new Tag());
        return "admin/tags_input";

    }

    //点击修改传递ID 到修改页面
    @GetMapping("/tags/{id}/input")
    public  String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tags",tagsService.getTags(id));

        return "admin/tags_input";

    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){

//        重复验证有待修改有bug
//        Tag T = tagsService.getTagsName(tag.getName());
//        if (T != null){
//            result.rejectValue("name","nameError","该名称已存在不能重复添加");
//
//        }
//
//        if (result.hasErrors()){
//            return "admin/tags_input";
//
//        }

        Tag t =  tagsService.saveTags(tag);
        if (t == null){
//
                attributes.addFlashAttribute("message" , "新增失败了！!");
        }else {
//
                attributes.addFlashAttribute("message" , "新增成功了!");
        }
        return "redirect:/admin/tags";
    }



//    修改方法

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){

//        重复验证有待 修改有bug
//        Tag T = tagsService.getTagsName(tag.getName());
//        if (T != null){
//            result.rejectValue("name","nameError","该名称已存在不能重复添加");
//
//        }
//
//        if (result.hasErrors()){
//            return "admin/tags_input";
//
//        }

        Tag t =  tagsService.updateTags(id,tag);
        if (t == null){
//
            attributes.addFlashAttribute("message" , "更新失败了!");
        }else {
//
            attributes.addFlashAttribute("message" , "更新成功了!");
        }
        return "redirect:/admin/tags";
    }

//    删除方法
    @GetMapping("/tags/{id}/delete")
    public  String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagsService.deleteTags(id);
        attributes.addFlashAttribute("message" , "删除成功！！!");


        return "redirect:/admin/tags";


    }

}
