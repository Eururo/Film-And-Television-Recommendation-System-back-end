package com.recommend.controller;

import com.recommend.entity.Favorites;
import com.recommend.entity.RecommendList;
import com.recommend.repository.RecommendListItemRepository;
import com.recommend.repository.RecommendListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/recommendList")
public class RecommendListController {

    @Autowired
    private RecommendListRepository recommendListRepository;
    @Autowired
    private RecommendListItemRepository recommendListItemRepository;

    @GetMapping("/findAll")
    public List<RecommendList> findAll(){
        return recommendListRepository.findAll();
    }

    @GetMapping("/findById/{list_id}")
    public RecommendList findByListId(@PathVariable("list_id") Integer list_id){
        return recommendListRepository.findById(list_id);
    }

    @GetMapping("/findByUserIdAndListName")
    public RecommendList findByUserIdAndListName(Integer user_id,String list_name){
        return recommendListRepository.findByUserIdAndListName(user_id,list_name);
    }

    @GetMapping("/findByUserId/{user_id}")
    public List<RecommendList> findByUserId(@PathVariable("user_id") Integer user_id){
        return recommendListRepository.findByUserId(user_id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody RecommendList recommendList){
        RecommendList recommendList1 = recommendListRepository.findByUserIdAndListName(recommendList.getUser_id(),recommendList.getList_name());
        if(recommendList1 != null) {
            return 0;
        }
        else{
            return recommendListRepository.insert(recommendList);
        }
    }

    @PostMapping("/update")
    public int updateById(@RequestBody RecommendList recommendList){
        return recommendListRepository.updateById(recommendList);
    }

    @GetMapping("/deleteById/{list_id}")
    public int deleteById(@PathVariable("list_id") Integer list_id){
        int result = recommendListItemRepository.deleteByListId(list_id);
        if(result != 0) {
            System.out.println("成功删除一个推荐列表");
        }
        return recommendListRepository.deleteById(list_id);
    }

    @GetMapping("/deleteByUserId/{user_id}")
    public int deleteByUserId(@PathVariable("user_id") Integer user_id){
        List<RecommendList> recommendLists = recommendListRepository.findByUserId(user_id);
        Iterator<RecommendList> iterator = recommendLists.iterator();
        if(iterator.hasNext()){
            RecommendList recommendList = iterator.next();
            int result = recommendListItemRepository.deleteByListId(recommendList.getList_id());
            if(result != 0) {
                System.out.println("成功删除推荐列表下的记录");
            }
            int temp = recommendListRepository.deleteById(recommendList.getList_id());
            if(temp != 0){
                System.out.println("成功删除该推荐列表");
            }
        }
        return 1;
    }

}
