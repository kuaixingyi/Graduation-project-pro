package com.kxy.bkd.dao;

import com.kxy.bkd.po.Tag;
import com.kxy.bkd.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);

}
