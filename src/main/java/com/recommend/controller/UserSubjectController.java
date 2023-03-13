package com.recommend.controller;

import com.recommend.entity.UserSubject;
import com.recommend.repository.UserSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/userSubject")
public class UserSubjectController {

    @Autowired
    private UserSubjectRepository userSubjectRepository;

    @GetMapping("/findUserSubject")
    public UserSubject findUserSubject(@RequestParam("user_id") Integer user_id,@RequestParam("subject_id") Integer subject_id){
        UserSubject result = userSubjectRepository.findByUserIdAndSubjectId(user_id,subject_id);
        if(result != null){
            return result;
        }
        UserSubject temp = new UserSubject();
        temp.setUser_id(user_id);
        temp.setSubject_id(subject_id);
        return temp;
    }

    @GetMapping("/findByUserIdAndSubjectId")
    public UserSubject findByUserIdAndSubjectId(@RequestParam("user_id") Integer user_id,@RequestParam("subject_id") Integer subject_id){
        return userSubjectRepository.findByUserIdAndSubjectId(user_id,subject_id);
    }

    @GetMapping("/findByUserIdAndStatus")
    public List<UserSubject> findByUserIdAndStatus(@RequestParam("user_id") Integer user_id,@RequestParam("status") String status){
        System.out.println(status);
        return userSubjectRepository.findByUserIdAndStatus(user_id,status);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody UserSubject userSubject){
        return userSubjectRepository.insert(userSubject);
    }

    @PostMapping("/update")
    public int update(@RequestBody UserSubject userSubject){
        return userSubjectRepository.update(userSubject);
    }

    @PostMapping("/deleteById")
    public int deleteById(@RequestBody UserSubject userSubject){
        return userSubjectRepository.deleteById(userSubject);
    }

    @GetMapping("/deleteByUserId/{user_id}")
    public int deleteByUserId(@PathVariable("user_id") Integer user_id){
        return userSubjectRepository.deleteByUserId(user_id);
    }

    @PostMapping("/updateUserSubject")
    public UserSubject updateUserSubject(@RequestBody UserSubject userSubject){
        UserSubject result = userSubjectRepository.findByUserIdAndSubjectId(userSubject.getUser_id(),userSubject.getSubject_id());
        if(result != null){
            if(userSubject.getIs_collected() == null){
                userSubject.setIs_collected(result.getIs_collected());
                userSubject.setCollected_time(result.getCollected_time());
            }
            else if(userSubject.getIs_collected()){
                Date date = new Date();
                Timestamp current_time = new Timestamp(date.getTime());
                userSubject.setCollected_time(current_time);
            }
            else{
                userSubject.setCollected_time(null);
            }
            if(userSubject.getIs_watched() == null){
                userSubject.setIs_watched(result.getIs_watched());
            }
            if(userSubject.getIs_watching() == null){
                userSubject.setIs_watching(result.getIs_watching());
            }
            if (userSubject.getScore() > 0.001) {
                userSubject.setIs_scored(true);
                userSubject.setScore(userSubject.getScore());
            }
            else if(result.getIs_scored() != null) {
                userSubject.setIs_scored(true);
                userSubject.setScore(result.getScore());
            }
            if(userSubject.getIs_like() == null){
                userSubject.setIs_like(result.getIs_like());
            }
            if(userSubject.getWatch_time() == null){
                userSubject.setWatch_time(result.getWatch_time());
            }
            if(userSubject.getRecommend_reason() == null){
                userSubject.setRecommend_reason(result.getRecommend_reason());
            }
            if(userSubject.getLink() == null){
                userSubject.setLink(result.getLink());
            }

            userSubjectRepository.update(userSubject);
        }
        else {
            if(userSubject.getWatch_time() == null){
                Date date = new Date();
                Timestamp current_time = new Timestamp(date.getTime());
                userSubject.setWatch_time(current_time);
            }
            if(userSubject.getIs_collected() != null){
                Date date = new Date();
                Timestamp current_time = new Timestamp(date.getTime());
                userSubject.setCollected_time(current_time);
            }
            userSubjectRepository.insert(userSubject);
        }
        return userSubjectRepository.findByUserIdAndSubjectId(userSubject.getUser_id(),userSubject.getSubject_id());
    }

    @GetMapping("/findUserSubjectByUserIdAndKind")
    public List<UserSubject> findUserSubjectByUserIdAndKind(Integer user_id,String kind,String order_by,Integer current_page,Integer page_size){
        current_page = (current_page - 1)*page_size;
        return userSubjectRepository.findUserSubjectByUserIdAndKind(user_id,kind,order_by,current_page,page_size);
    }

}
