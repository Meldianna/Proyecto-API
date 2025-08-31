package com.uade.tpo.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.demo.entity.FavoriteList;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList, Long> {
    Optional<FavoriteList> findByUserId(Long userId);
}
