package com.recommend.repository;

import com.recommend.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    public List<User> findAll();
    public User findById(Integer user_id);
    public User findUser(User user);
    public User findUserByName(User user);
    public List<User> searchUser(String search_param);
    public int insert(User user);
    public int updateById(User user);
    public int changePassword(User user);
    public int deleteById(Integer user_id);

}
