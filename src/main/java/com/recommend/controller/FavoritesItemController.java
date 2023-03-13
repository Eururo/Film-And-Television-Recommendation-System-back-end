package com.recommend.controller;

import com.recommend.entity.FavoritesItem;
import com.recommend.entity.Item;
import com.recommend.repository.FavoritesItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/favoritesItem")
public class FavoritesItemController {

    @Autowired
    private FavoritesItemRepository favoritesItemRepository;

    @GetMapping("/findById")
    public List<Item> findById(Integer favorites_id,String type,String order_by,Integer current_page,Integer page_size){
        current_page = (current_page - 1)*page_size;
        if(!order_by.equals("collected_time")){
            order_by = "s." + order_by;
        }
        return favoritesItemRepository.findById(favorites_id,type,order_by,current_page,page_size);
    }

    @GetMapping("/insert")
    public int insert(Integer subject_id,Integer favorites_id){
        return  favoritesItemRepository.insert(subject_id,favorites_id);
    }

    @GetMapping("/delete")
    public int delete(Integer subject_id,Integer favorites_id){
        return  favoritesItemRepository.delete(subject_id,favorites_id);
    }

    @GetMapping("/deleteByFavoritesId")
    public int deleteByFavoritesId(Integer favorites_id){
        return favoritesItemRepository.deleteByFavoritesId(favorites_id);
    }

}
