package com.kxy.bkd.service;

import com.kxy.bkd.po.Blog;
import com.kxy.bkd.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> ListBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);


}
