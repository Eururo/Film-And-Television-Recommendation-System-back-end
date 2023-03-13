package com.recommend.repository;

import com.recommend.entity.Favorites;
import com.recommend.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository {

    public Favorites findById(Integer favorites_id);
    public Favorites findByUserIdAndFavoritesName(Integer user_id,String favorites_name);
    public List<Favorites> findByUserId(Integer user_id);
    public int insert(Favorites favorites);
    public int update(Favorites favorites);
    public int deleteById(Integer favorites_id);
    public int deleteByUserId(Integer user_id);
}
