package com.uade.tpo.demo.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uade.tpo.demo.entity.Bill;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    
    
    @Query(value = "select b from Bill b where b.order.user.id = :userId")
    Page<Bill> findByUserId(@Param("userId") Long userId, Pageable pageable);
    
    @Query(value = "select b from Bill b where b.order.id =:orderId")
    Optional<Bill> findByOrderId(@Param("orderId")Long orderId);

    @Query(value = "select b from Bill b where b.date = :date")
    Page<Bill> findByDate(@Param("date")LocalDate date, Pageable pageable);
}
