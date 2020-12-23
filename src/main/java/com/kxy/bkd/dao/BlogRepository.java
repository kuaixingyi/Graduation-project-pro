package com.kxy.bkd.dao;

import com.kxy.bkd.po.Blog;
import com.kxy.bkd.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog ,Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from T_blog b where b.recommend = true ")
    List<Blog> findTop(Pageable pageable);

    @Query("select  b from T_blog b where  b.title like  ?1 or  b.content like ?1")
    Page<Blog> findByQuery(String query,Pageable pageable);


}
