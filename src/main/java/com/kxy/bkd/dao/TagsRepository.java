package com.kxy.bkd.dao;

import com.kxy.bkd.po.Tag;
import com.kxy.bkd.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
}
