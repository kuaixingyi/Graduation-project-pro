package com.kxy.bkd.web;


import com.kxy.bkd.po.Type;
import com.kxy.bkd.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TpyController {

    @Autowired
    private TypeService typeService;

//    分页查询方式
    @GetMapping("/types")
    public String types(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){

        model.addAttribute("page",typeService.listType(pageable));

        return "admin/types";



    }
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types_input";

    }

    //点击修改传递ID 到修改页面
    @GetMapping("/types/{id}/input")
    public  String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));

        return "admin/types_input";

    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){

//        重复验证
        Type type1 = typeService.getTypeName(type.getName());
        if (type1 != null){
            result.rejectValue("name","nameError","该名称已存在不能重复添加");

        }

        if (result.hasErrors()){
            return "admin/types_input";

        }

        Type t =  typeService.saveType(type);
        if (t == null){
//
                attributes.addFlashAttribute("message" , "新增失败了！!");
        }else {
//
                attributes.addFlashAttribute("message" , "新增成功了!");
        }
        return "redirect:/admin/types";
    }



//    修改方法

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){

//        重复验证
        Type type1 = typeService.getTypeName(type.getName());
        if (type1 != null){
            result.rejectValue("name","nameError","该名称已存在不能重复添加");

        }

        if (result.hasErrors()){
            return "admin/types_input";

        }

        Type t =  typeService.updateType(id,type);
        if (t == null){
//
            attributes.addFlashAttribute("message" , "更新失败了!");
        }else {
//
            attributes.addFlashAttribute("message" , "更新成功了!");
        }
        return "redirect:/admin/types";
    }

//    删除方法
    @GetMapping("/types/{id}/delete")
    public  String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message" , "删除成功！！!");


        return "redirect:/admin/types";


    }

}
