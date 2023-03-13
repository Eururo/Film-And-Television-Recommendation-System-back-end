package com.recommend.repository;

import com.recommend.entity.RecommendListCard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendListCardRepository {

    public List<RecommendListCard> findByListId(Integer list_id,String type,String order_by,Integer current_page,Integer page_size);
}
