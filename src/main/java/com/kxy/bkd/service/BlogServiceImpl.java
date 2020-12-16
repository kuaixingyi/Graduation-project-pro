package com.kxy.bkd.service;

import com.kxy.bkd.dao.BlogRepository;
import com.kxy.bkd.po.Blog;
import com.kxy.bkd.po.Type;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


//增，删，查，改，分页方法
@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findById(id).get();
    }


//    分页动态查询方法(使用jpa封装方法)


    @Override
    public Page<Blog> ListBlog(Pageable pageable, Blog blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null){
//                    拼接查询条件动态组合
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));


                }

                if (blog.getType().getId() !=null){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blog.getType().getId()));

                }
                if (blog.isRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blog.isRecommend()));

                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

                return null;
            }
        },pageable);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {

        Blog b = blogRepository.findById(id).get();
        if (b == null) {
            try {
                throw new NotFoundException("该消息不存在");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }

        }
        BeanUtils.copyProperties(b, blog);

        return blogRepository.save(b);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);

    }
}

