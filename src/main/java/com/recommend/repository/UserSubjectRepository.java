package com.recommend.repository;

import com.recommend.entity.User;
import com.recommend.entity.UserSubject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSubjectRepository {

    public UserSubject findByUserIdAndSubjectId(Integer user_id,Integer subject_id);
    public List<UserSubject> findByUserIdAndStatus(Integer user_id,String status);
    public List<UserSubject> findUserSubjectByUserIdAndKind(Integer user_id,String kind,String order_by,Integer current_page,Integer page_size);
    public int insert(UserSubject userSubject);
    public int update(UserSubject userSubject);
    public int deleteById(UserSubject userSubject);
    public int deleteByUserId(Integer user_id);
}
