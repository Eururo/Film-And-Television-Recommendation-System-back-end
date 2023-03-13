package com.recommend.repository;

import com.recommend.entity.RecommendList;
import com.recommend.entity.Subscribe;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeRepository {

    public List<RecommendList> findByUserId(Integer user_id);
    public Subscribe findByUserIdAndListId(Integer user_id,Integer list_id);
    public int insert(Subscribe subscribe);
    public int deleteById(Subscribe subscribe);
    public int deleteByUserId(Integer user_id);
}
