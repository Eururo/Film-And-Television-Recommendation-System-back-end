package com.recommend.controller;

import com.recommend.entity.Favorites;
import com.recommend.repository.FavoritesItemRepository;
import com.recommend.repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesRepository favoritesRepository;
    @Autowired
    private FavoritesItemRepository favoritesItemRepository;

    @GetMapping("/findById/{favorites_id}")
    public Favorites findById(@PathVariable("favorites_id") Integer favorites_id){
        return favoritesRepository.findById(favorites_id);
    }

    @GetMapping("/findByUserIdAndFavoritesName")
    public Favorites findByUserIdAndFavoritesName(@RequestParam("user_id") Integer user_id,@RequestParam("favorites_name")String favorites_name){
        Favorites favorites = favoritesRepository.findByUserIdAndFavoritesName(user_id,favorites_name);
        if(favorites != null) {
            return favoritesRepository.findByUserIdAndFavoritesName(user_id, favorites_name);
        }
        else{
            Favorites favorites1 = new Favorites();
            return  favorites1;
        }
        //        return favoritesRepository.findByUserIdAndFavoritesName(user_id,favorites_name);
    }

    @GetMapping("/findByUserId/{user_id}")
    public List<Favorites> findByUserId(@PathVariable("user_id") Integer user_id){
        return favoritesRepository.findByUserId(user_id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Favorites favorites){
        Favorites favorites1 = favoritesRepository.findByUserIdAndFavoritesName(favorites.getUser_id(),favorites.getFavorites_name());
        if(favorites1 != null){
            return 0;
        }
        return favoritesRepository.insert(favorites);
    }

    @PostMapping("/update")
    public int update(@RequestBody Favorites favorites){
        return favoritesRepository.update(favorites);
    }

    @GetMapping("/deleteById/{favorites_id}")
    public int deleteById(@PathVariable("favorites_id") Integer favorites_id){
        int result = favoritesItemRepository.deleteByFavoritesId(favorites_id);
        if(result != 0) {
            System.out.println("成功删除一个收藏夹");
        }
        return favoritesRepository.deleteById(favorites_id);
    }

    @GetMapping("/deleteByUserId/{user_id}")
    public int deleteByUserId(@PathVariable("user_id") Integer user_id){
        List<Favorites> favoritesList = favoritesRepository.findByUserId(user_id);
        Iterator<Favorites> iterator = favoritesList.iterator();
        if(iterator.hasNext()){
            Favorites favorites = iterator.next();
            int result = favoritesItemRepository.deleteByFavoritesId(favorites.getFavorites_id());
            if(result != 0) {
                System.out.println("成功删除收藏夹下的记录");
            }
            int temp = favoritesRepository.deleteById(favorites.getFavorites_id());
            if(temp != 0){
                System.out.println("成功删除该收藏夹");
            }
        }
        return 1;
    }
}
