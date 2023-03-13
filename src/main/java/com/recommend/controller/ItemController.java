package com.recommend.controller;

import com.recommend.repository.ItemRepository;
import com.recommend.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personalPage")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/findItemList")
    public List<Item> findItemList(Integer user_id,String kind,String type,String order_by,Integer current_page,Integer page_size){
        current_page = (current_page - 1) * page_size;
        if(!order_by.equals("collected_time")){
            order_by = "s." + order_by;
        }
        return itemRepository.findItemList(user_id,kind,type,order_by,current_page,page_size);
    }

    @GetMapping("/findWatchingList")
    public List<Item> findWatchingList(Integer user_id,String type,String order_by,Integer current_page,Integer page_size){
        current_page = (current_page - 1) * page_size;
        if(!order_by.equals("collected_time")){
            order_by = "s." + order_by;
        }
        return itemRepository.findWatchingList(user_id,type,order_by,current_page,page_size);
    }

}
