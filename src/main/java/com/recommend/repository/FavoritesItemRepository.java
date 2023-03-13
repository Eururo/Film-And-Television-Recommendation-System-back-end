package com.recommend.repository;

import com.recommend.entity.FavoritesItem;
import com.recommend.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesItemRepository {

    public List<Item> findById(Integer favorites_id,String type,String order_by,Integer current_page,Integer page_size);
    public int insert(Integer subject_id,Integer favorites_id);
    public int delete(Integer subject_id,Integer favorites_id);
    public int deleteByFavoritesId(Integer favorites_id);
}
