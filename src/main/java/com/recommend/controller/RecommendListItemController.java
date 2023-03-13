package com.recommend.controller;


import com.recommend.entity.Item;
import com.recommend.repository.RecommendListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendListItem")
public class RecommendListItemController {

    @Autowired
    private RecommendListItemRepository recommendListItemRepository;

    @GetMapping("/findByListId")
    public List<Item> findByListId(Integer list_id, String type, String order_by, Integer current_page, Integer page_size){
        current_page = (current_page - 1)*page_size;
        if(!order_by.equals("add_time")){
            order_by = "s." + order_by;
        }
        return recommendListItemRepository.findByListId(list_id,type,order_by,current_page,page_size);
    }

    @GetMapping("/insert")
    public int insert(Integer subject_id,Integer list_id){
        return recommendListItemRepository.insert(subject_id,list_id);
    }

    @GetMapping("/delete")
    public int delete(Integer subject_id,Integer list_id){
        return recommendListItemRepository.delete(subject_id,list_id);
    }

    @GetMapping("/deleteByListId")
    public int deleteByListId(Integer list_id){
        return recommendListItemRepository.deleteByListId(list_id);
    }
}
