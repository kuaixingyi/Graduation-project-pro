package com.kxy.bkd.web;


import com.kxy.bkd.po.Type;
import com.kxy.bkd.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String input(){
        return "admin/types_input";

    }
    @PostMapping("types")
    public String post(Type type){
        Type t =  typeService.saveType(type);
        if (t == null){
//
        }else {
//
        }
        return "redirect:/admin/types";
    }
}
