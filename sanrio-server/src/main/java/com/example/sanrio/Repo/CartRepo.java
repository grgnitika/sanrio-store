package com.example.sanrio.Repo;

import com.example.sanrio.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {


    List<Cart> findByUserId(Long userId);

    @Query(value = "Delete from carts where user_id=?1",nativeQuery = true)
    void deleteByUserId(Long user_id);
}
