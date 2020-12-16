package com.kxy.bkd.service;

import com.kxy.bkd.dao.TagsRepository;
import com.kxy.bkd.po.Tag;
import com.kxy.bkd.po.Type;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagsServiceImpl implements TagsService{

    @Autowired
    private TagsRepository tagsRepository;

    @Transactional
    @Override
    public Tag saveTags(Tag tag) {
        return tagsRepository.save(tag);
    }
    @Transactional
    @Override
    public Tag getTags(Long id) {
        return tagsRepository.findById(id).get();
    }

    @Override
    public Tag getTagsName(String name) {
        return tagsRepository.findByName(name);
    }
    @Transactional
    @Override
    public Page<Tag> listTags(Pageable pageable) {
        return tagsRepository.findAll(pageable);
    }
    @Transactional
    @Override
    public Tag updateTags(Long id, Tag tag) {

        Tag type1 = tagsRepository.findById(id).get();
        if (type1 == null) {
            try {
                throw new NotFoundException("不存在该类型");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        BeanUtils.copyProperties(tag,type1);
//        Type a = new Type();
//        a.setId(type1.get().getId());
//        a.setBlogs(type1.get().getBlogs());
//        a.setName(type1.get().getName());
//        return typeRepository.save(a);
        return tagsRepository.save(type1);

    }
    @Transactional
    @Override
    public void deleteTags(Long id) {
        tagsRepository.deleteById(id);

    }
}
