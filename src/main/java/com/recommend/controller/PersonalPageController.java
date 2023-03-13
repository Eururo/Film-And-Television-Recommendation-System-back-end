package com.recommend.controller;

import com.recommend.entity.Subject;
import com.recommend.entity.UserSubject;
import com.recommend.repository.SubjectRepository;
import com.recommend.repository.UserSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/personalPage")
public class PersonalPageController {

    @Autowired
    private UserSubjectRepository userSubjectRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/getSubjectListByUserIdAndKind")
    public List<Subject> getSubjectListByUserIdAndKind(Integer user_id, String kind, String order_by, Integer current_page, Integer page_size){
        List<Subject> subjectList = new ArrayList<>();;
        current_page = (current_page - 1)*page_size;
        List<UserSubject> userSubjectList = userSubjectRepository.findUserSubjectByUserIdAndKind(user_id,kind,order_by,current_page,page_size);
        Iterator<UserSubject> it = userSubjectList.iterator();
        while(it.hasNext()){
            UserSubject userSubject = it.next();
            Subject subject = subjectRepository.findSubjectById(userSubject.getSubject_id());
            subjectList.add(subject);
        }
        return subjectList;
    }
}
