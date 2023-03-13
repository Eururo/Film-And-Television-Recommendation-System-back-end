package com.recommend.repository;

import com.recommend.entity.Subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository {

    public List<Subject> findAll();
    public Subject findSubjectById(Integer subject_id);
    public List<Subject> searchSubject(String search_param,String order_by,Integer current_page,Integer page_size);
    public List<Subject> searchSubjectByKind(String search_param,String kind,String order_by,Integer current_page,Integer page_size);
    public List<Subject> findSubjectByKind(String kind,String order_by,Integer current_page,Integer page_size);

}
