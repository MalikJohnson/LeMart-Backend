package com.LeMart.repo;

import com.LeMart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId); // Find cart by user ID
    
    
    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    List<Cart> findAllByUserId(@Param("userId") Long userId);
    
    default Cart findCartByUserId(Long userId) {
        List<Cart> carts = findAllByUserId(userId);
        return carts.isEmpty() ? null : carts.get(0); // Return first cart if duplicates exist
    }
}