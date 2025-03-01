package com.LeMart.repo;

import com.LeMart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId); // Find cart items by cart ID
    void deleteByCartId(Long cartId); // Delete cart items by cart ID
}