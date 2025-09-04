package com.uade.tpo.demo.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.demo.entity.FavoriteList;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList, Long> {
    @Query(value = "SELECT fl FROM FavoriteList fl WHERE fl.user.id = :userId")
    List<FavoriteList> findByUserId(Long userId);
}
