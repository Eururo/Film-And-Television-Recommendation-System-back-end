package com.recommend.repository;

import com.recommend.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendListItemRepository {

    public List<Item> findByListId(Integer list_id,String type,String order_by,Integer current_page,Integer page_size);
    public int insert(Integer subject_id,Integer list_id);
    public int delete(Integer subject_id,Integer list_id);
    public int deleteByListId(Integer list_id);
}
