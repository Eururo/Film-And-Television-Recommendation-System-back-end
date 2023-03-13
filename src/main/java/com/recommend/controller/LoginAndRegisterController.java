package com.recommend.controller;

import com.recommend.entity.User;
import com.recommend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class LoginAndRegisterController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoritesRepository favoritesRepository;
    @Autowired
    private UserSubjectRepository userSubjectRepository;
    @Autowired
    private RecommendListRepository recommendListRepository;
    @Autowired
    private SubscribeRepository subscribeRepository;

    @GetMapping("/isLogin")
    public Integer isLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer result = (Integer) session.getAttribute("user_id");
        if(result != null){
            return result;
        }
        return 0;
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, @RequestBody User user){
        User result = userRepository.findUserByName(user);
        if (result != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user_id", result.getUser_id());
            return "登录成功";
        } else {
            return "用户名或密码错误";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("user_id") != null){
            session.removeAttribute("user_id");
            return "退出登录";
        }
        else {
            return "用户未登录";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        User result = userRepository.findUserByName(user);
        if(result != null) {
            return "用户名已被其他用户使用，请重新输入";
        }
        else {
            int temp = userRepository.insert(user);
            if(temp == 1) {
                return "注册成功";
            }
            else {
                return "注册失败";
            }
        }
    }

    @GetMapping("/deleteUser/{user_id}")
    public Integer deleteUser(HttpServletRequest request,@PathVariable("user_id") Integer user_id){
        System.out.println("开始删除用户信息");
        int num = favoritesRepository.deleteByUserId(user_id);
        if(num != 0){
            System.out.println("成功删除该用户的收藏夹");
        }
        num = recommendListRepository.deleteByUserId(user_id);
        if(num != 0){
            System.out.println("成功删除该用户的推荐列表");
        }
        int temp = userSubjectRepository.deleteByUserId(user_id);
        if(temp != 0){
            System.out.println("成功删除该用户与电影的交互记录");
        }
        int count = subscribeRepository.deleteByUserId(user_id);
        if(count != 0){
            System.out.println("成功删除该用户的订阅记录");
        }
        int result = userRepository.deleteById(user_id);
        if(result == 1) {
            HttpSession session = request.getSession();
            if(session.getAttribute("user_id") != null){
                session.removeAttribute("user_id");
                System.out.println("注销用户");
            }
            return 1;
        }
        else {
            return 0;
        }
    }
}
