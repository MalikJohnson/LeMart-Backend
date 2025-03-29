package com.LeMart.service;

import com.LeMart.exception.CartItemNotFoundException;
import com.LeMart.model.CartItem;
import com.LeMart.repo.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepo cartItemRepo;

    @Autowired
    public CartItemService(CartItemRepo cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }

    @Transactional
    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepo.save(cartItem);
    }

    public List<CartItem> findCartItemsByCartId(Long cartId) {
        return cartItemRepo.findByCartId(cartId);
    }

    public CartItem findCartItemById(Long id) {
        return cartItemRepo.findById(id)
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found with ID: " + id));
    }

    @Transactional
    public CartItem updateCartItem(CartItem cartItem) {
        // Verify the item exists first
        if (!cartItemRepo.existsById(cartItem.getId())) {
            throw new CartItemNotFoundException("CartItem not found with ID: " + cartItem.getId());
        }
        return cartItemRepo.save(cartItem);
    }

    @Transactional
    public void deleteCartItem(Long id) {
        cartItemRepo.deleteById(id);
    }

    @Transactional
    public void deleteCartItemsByCartId(Long cartId) {
        cartItemRepo.deleteByCartId(cartId);
    }
}