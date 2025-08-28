package com.uade.tpo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.FavoriteList;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.repository.FavoriteListRepository;
import com.uade.tpo.demo.repository.UserRepository;

@Service
public class FavotiteListServiceImpl implements FavoriteListService {

    @Autowired
    private FavoriteListRepository favoriteListRepository;
    private UserRepository userRepository;
    
    @Override
    public FavoriteList createFavoriteList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FavoriteList favoriteList = new FavoriteList();
        favoriteList.setUser(user);
        return favoriteListRepository.save(favoriteList);

    }

    @Override
    public void delete(FavoriteList favoriteList) {
        
    }

    @Override
    public FavoriteList update(FavoriteList favoriteList) {

        return favoriteListRepository.save(favoriteList);
        
    }

    @Override
    public void deleteById(Long id) {
        favoriteListRepository.deleteById(id);
    }

    @Override
    public List<FavoriteList> findByUserId(Long userId) {
        return favoriteListRepository.findAll();

    }

    @Override
    public FavoriteList findById(Long id) {
        return favoriteListRepository.findById(id).orElse(null);
    }
    
}
