package com.kxy.bkd.service;

import com.kxy.bkd.dao.CommentRepository;
import com.kxy.bkd.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author ：快兴毅
 * @date ：Created in 2020/12/19 18:15
 * @description：
 * @modified By：
 * @version: $
 */

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {

        Sort sort = Sort.by(Sort.Order.desc("createTime"));
        return commentRepository.findByBlogId(blogId,sort);
    }
//通过ID 判断父子关系的回复逻辑
    @Transactional
    @Override
    public Comment saveComment(Comment comment) {

        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1){
            comment.setParentComment(commentRepository.findById(parentCommentId).get());

        }else {
            comment.setParentComment(null);

        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }
}
