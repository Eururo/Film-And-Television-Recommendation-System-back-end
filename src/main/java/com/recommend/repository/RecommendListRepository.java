package com.recommend.repository;

import com.recommend.entity.RecommendList;
import com.recommend.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendListRepository {

    public List<RecommendList> findAll();
    public RecommendList findById(Integer list_id);
    public RecommendList findByUserIdAndListName(Integer user_id,String list_name);
    public List<RecommendList> findByUserId(Integer user_id);
    public int insert(RecommendList recommendList);
    public int updateById(RecommendList recommendList);
    public int deleteById(Integer list_id);
    public int deleteByUserId(Integer user_id);
}
