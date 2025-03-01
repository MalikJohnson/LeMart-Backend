package com.LeMart.service;

import com.LeMart.exception.CartNotFoundException;
import com.LeMart.model.Cart;
import com.LeMart.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepo cartRepo;

    @Autowired
    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public Cart createCart(Cart cart) {
        return cartRepo.save(cart);
    }

    public Cart findCartByUserId(Long userId) {
        return cartRepo.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart for user with id " + userId + " does not exist"));
    }

    public void deleteCart(Long id) {
        cartRepo.deleteById(id);
    }
}