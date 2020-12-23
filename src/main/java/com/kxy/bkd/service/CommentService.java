package com.kxy.bkd.service;

import com.kxy.bkd.po.Comment;

import java.util.List;

/**
 * @author ：快兴毅
 * @date ：Created in 2020/12/19 18:12
 * @description：
 * @modified By：
 * @version: $
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
