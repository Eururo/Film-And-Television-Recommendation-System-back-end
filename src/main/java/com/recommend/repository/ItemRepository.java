package com.recommend.repository;

import com.recommend.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository {

    public List<Item> findItemList(Integer user_id,String kind,String type,String order_by,Integer current_page,Integer page_size);
    public List<Item> findWatchingList(Integer user_id,String type,String order_by,Integer current_page,Integer page_size);
}
