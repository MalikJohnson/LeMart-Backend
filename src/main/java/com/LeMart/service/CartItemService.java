package com.LeMart.service;

import com.LeMart.exception.CartItemNotFoundException;
import com.LeMart.model.CartItem;
import com.LeMart.repo.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepo cartItemRepo;

    @Autowired
    public CartItemService(CartItemRepo cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }

    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepo.save(cartItem);
    }

    public List<CartItem> findCartItemsByCartId(Long cartId) {
        return cartItemRepo.findByCartId(cartId);
    }

    public void deleteCartItem(Long id) {
        cartItemRepo.deleteById(id);
    }

    public void deleteCartItemsByCartId(Long cartId) {
        cartItemRepo.deleteByCartId(cartId);
    }
}