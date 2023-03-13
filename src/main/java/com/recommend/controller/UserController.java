package com.recommend.controller;

import com.recommend.entity.User;
import com.recommend.repository.*;
import com.recommend.utils.BeanCopyUtils;
import com.recommend.vo.UserInfoVo;
import com.recommend.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.recommend.utils.BeanCopyUtils.copyBean;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/findAll")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/findById/{user_id}")
    public UserInfoVo findById(@PathVariable("user_id") Integer user_id){
        User response = userRepository.findById(user_id);
        if(response != null) {
            UserInfoVo result = copyBean(response, UserInfoVo.class);
            return result;
        }
        else{
            return new UserInfoVo();
        }
    }

    @PostMapping("/findUser")
    public User findUser(@RequestBody User user){
        return userRepository.findUser(user);
    }

    @PostMapping("/findUserByName")
    public User findUserByName(@RequestBody User user){
        return userRepository.findUserByName(user);
    }

    @GetMapping("/searchUser")
    public List<User> searchUser(@RequestParam("search_param") String search_param){
        return userRepository.searchUser(search_param);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody User user){
        return userRepository.insert(user);
    }

    @PostMapping("/updateById")
    public int updateById(@RequestBody User user){
        return userRepository.updateById(user);
    }

    @GetMapping("/deleteById/{user_id}")
    public int deleteById(@PathVariable("user_id") Integer user_id){
        return userRepository.deleteById(user_id);
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody User user){
        int temp = userRepository.changePassword(user);
        if(temp == 1) {
            return "密码修改成功";
        }
        else{
            return "密码修改失败";
        }
    }

}
