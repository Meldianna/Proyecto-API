package com.uade.tpo.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uade.tpo.demo.entity.Bill;
import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.User;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    
    @Query(value = "select b from Bill b where b.idOrder.user = :user")
    Page<Bill> findByUserId(@Param("user") User user, Pageable pageable);

    @Query(value = "select b from Bill b where b.idOrder =:idOrder")
    Optional<Bill> findByOrderId(Order idOrder);

    @Query(value = "select b from Bill b where b.date = :date")
    Page<Bill> findByDate(LocalDate date, Pageable pageable);
}
