package com.kxy.bkd.service;

import com.kxy.bkd.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> ListBlog(Pageable pageable,Blog blog);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);


}
