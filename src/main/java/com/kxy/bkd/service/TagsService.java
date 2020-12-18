package com.kxy.bkd.service;

import com.kxy.bkd.po.Tag;
import com.kxy.bkd.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//定义增，删，改，查接口,分页查询接口方法
public interface TagsService {

    Tag saveTags(Tag tag);

    Tag getTags(Long id);

    Tag getTagsName(String name);

    Page<Tag> listTags(Pageable pageable);

    List <Tag> lisTag();

    List<Tag> lisTag(String ids);


    Tag updateTags(Long id,Tag tag);

    void deleteTags(Long id);

}
