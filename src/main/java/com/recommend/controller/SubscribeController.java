package com.recommend.controller;

import com.recommend.entity.RecommendList;
import com.recommend.entity.Subscribe;
import com.recommend.repository.SubjectRepository;
import com.recommend.repository.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    @Autowired
    private SubscribeRepository subscribeRepository;

    @GetMapping("/findByUserId/{user_id}")
    public List<RecommendList> findByUserId(@PathVariable("user_id") Integer user_id){
        return subscribeRepository.findByUserId(user_id);
    }

    @GetMapping("/findByUserIdAndListId")
    public Subscribe findByUserIdAndListId(Integer user_id,Integer list_id){
        Subscribe subscribe = subscribeRepository.findByUserIdAndListId(user_id,list_id);
        if(subscribe != null){
            return subscribe;
        }
        else{
            return new Subscribe();
        }
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Subscribe subscribe){
        return subscribeRepository.insert(subscribe);
    }

    @PostMapping("/deleteById")
    public int deleteById(@RequestBody Subscribe subscribe){
        return subscribeRepository.deleteById(subscribe);
    }

    @GetMapping("/deleteByUserId")
    public int deleteByUserId(Integer user_id){
        return subscribeRepository.deleteByUserId(user_id);
    }
}
