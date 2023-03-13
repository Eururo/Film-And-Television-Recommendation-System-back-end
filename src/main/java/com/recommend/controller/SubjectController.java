package com.recommend.controller;

import com.recommend.entity.Subject;
import com.recommend.repository.SubjectRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/findAll")
    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    @GetMapping("/findSubjectById/{subject_id}")
    public Subject findSubjectById(@PathVariable("subject_id") Integer subject_id){
        return subjectRepository.findSubjectById(subject_id);
    }

    @GetMapping("/searchSubject")
    public List<Subject> searchSubject(@RequestParam String search_param, @RequestParam String order_by, @RequestParam Integer current_page, @RequestParam Integer page_size){
        current_page = (current_page - 1)*page_size;
        return subjectRepository.searchSubject(search_param, order_by, current_page, page_size);
    }

    @GetMapping("/searchSubjectByKind")
    public List<Subject> searchSubjectByKind(@RequestParam String search_param, @RequestParam String order_by, @RequestParam("kind") String kind, @RequestParam("current_page") Integer current_page,@RequestParam("page_size") Integer page_size){
        current_page = (current_page - 1) * page_size;
        if(kind.equals("all")){
            return subjectRepository.searchSubject(search_param, order_by, current_page, page_size);
        }
        else {
            kind = "is_" + kind;
            if (order_by.equals("recommend")) {
                order_by = "score";
            }
            return subjectRepository.searchSubjectByKind(search_param, kind, order_by, current_page, page_size);
        }
    }

    @GetMapping("/findSubjectByKind")
    public List<Subject> findSubjectByKind(String kind,String order_by,Integer current_page,Integer page_size){
        if(order_by.equals("recommend")){
            order_by = "score";
        }
        current_page = (current_page - 1)*page_size;
        return subjectRepository.findSubjectByKind(kind,order_by,current_page,page_size);
    }

}
