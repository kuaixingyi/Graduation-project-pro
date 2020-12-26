package com.kxy.bkd.service;

import com.kxy.bkd.dao.CommentRepository;
import com.kxy.bkd.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

        Sort sort = Sort.by(Sort.Order.asc("createTime"));
        List<Comment> comments =  commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(comments);
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


//    循环每一个顶级的评论节点
    private List<Comment> eachComment(List<Comment> comments) {
//        System.out.println("顶级comments:"+comments);
//        copy一个新的集合避免对原有数据的损坏
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }


//    根结点不为空的对象集合
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

//    递归迭代，剥洋葱
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }
}
