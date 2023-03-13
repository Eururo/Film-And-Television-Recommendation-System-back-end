package com.recommend.controller;

import com.recommend.entity.RecommendListCard;
import com.recommend.repository.RecommendListCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendListCard")
public class RecommendListCardController {

    @Autowired
    private RecommendListCardRepository recommendListCardRepository;

    @GetMapping("/findByListId")
    public List<RecommendListCard> findByListId(Integer list_id, String type, String order_by, Integer current_page, Integer page_size){
        current_page = (current_page - 1) * page_size;
        if(!order_by.equals("add_time")){
            order_by = "s." + order_by;
        }
        return recommendListCardRepository.findByListId(list_id,type,order_by,current_page,page_size);
    }

}
