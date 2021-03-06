package com.kxy.bkd.dao;

import com.kxy.bkd.po.Tag;
import com.kxy.bkd.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);

    //    自定义查询
    @Query("select t from T_tag t")
    List<Tag> findTop(Pageable pageable);

}
