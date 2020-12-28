package com.kxy.bkd.dao;

import com.kxy.bkd.po.Blog;
import com.kxy.bkd.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog ,Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from T_blog b where b.recommend = true ")
    List<Blog> findTop(Pageable pageable);

    @Query("select  b from T_blog b where  b.title like  ?1 or  b.content like ?1")
    Page<Blog> findByQuery(String query,Pageable pageable);


    @Transactional
    @Modifying
    @Query("update T_blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);


    @Query(value = "SELECT date_format(b.update_time,'%Y') as year from t_blog b group by year order by year DESC",nativeQuery = true)
    List<String> findGroupYear();

    @Query("select b from T_blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

}
