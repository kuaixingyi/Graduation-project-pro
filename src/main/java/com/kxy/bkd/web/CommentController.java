package com.kxy.bkd.web;

import com.kxy.bkd.po.Comment;
import com.kxy.bkd.po.User;
import com.kxy.bkd.service.BlogService;
import com.kxy.bkd.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author ：快兴毅
 * @date ：Created in 2020/12/19 17:02
 * @description：
 * @modified By：
 * @version: $
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

//    评论方法
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));

        return "blog :: commentList";

    }

//    回复评论方法
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
            comment.setNickname(user.getNickname());

        }else {
            comment.setAvatar(avatar);
        }

        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId ;
    }

}
