package com.kxy.bkd.dao;

import com.kxy.bkd.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：快兴毅
 * @date ：Created in 2020/12/19 18:17
 * @description：
 * @modified By：
 * @version: $
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByBlogId(Long blogId, Sort sort);
}
