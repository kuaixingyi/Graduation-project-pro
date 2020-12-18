package com.kxy.bkd.service;

import com.kxy.bkd.po.Blog;
import com.kxy.bkd.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> ListBlog(Pageable pageable, BlogQuery blog);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);


}
