package com.kxy.bkd.web.admin;

import com.kxy.bkd.po.Blog;
import com.kxy.bkd.po.User;
import com.kxy.bkd.service.BlogService;
import com.kxy.bkd.service.TagsService;
import com.kxy.bkd.service.TypeService;
import com.kxy.bkd.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

//    定向优化
    private static final String INPUT = "admin/admin_input";
    private static final String LIST = "admin/admins";
    private static final String REDIRECT_LIST = "redirect:/admin/admins";


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagsService tagsService;

    @GetMapping("/admins")
//    分页方式
    public String admins(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,  BlogQuery blog, Model model){

        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.ListBlog(pageable,blog));
        return LIST;

    }
    @PostMapping("/admins/search")
//    分页方式
    public String search(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){

        model.addAttribute("page",blogService.ListBlog(pageable,blog));
//        局部渲染
        return "admin/admins :: adminList";

    }
//    新增方法
    @GetMapping("/admins/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return INPUT;
    }
//  取值公共方法
    private  void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagsService.lisTag());
    }
//    修改传递ID 值到修改页面
    @GetMapping("/admins/{id}/input")
    public String editinput(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);

        return INPUT;
    }

//    修改新增共用方法)
    @PostMapping("/admins")
    public String post(Blog blog , RedirectAttributes attributes, HttpSession session ){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagsService.lisTag(blog.getTagIds()));
        Blog b ;

        if (blog.getId() == null){
            b = blogService.saveBlog(blog);

        }else {
            b = blogService.updateBlog(blog.getId(),blog);

        }
        if (b == null){
//
            attributes.addFlashAttribute("message" , "操作失败了！!");
        }else {
//
            attributes.addFlashAttribute("message" , "操作成功了!");
        }
        return REDIRECT_LIST;

    }

    @GetMapping("admins/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){


        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message" , "删除成功！！!");
        return REDIRECT_LIST;

    }

}
