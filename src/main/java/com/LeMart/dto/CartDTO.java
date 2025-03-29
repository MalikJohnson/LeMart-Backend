package com.LeMart.dto;

import java.util.List;

public class CartDTO {
    public CartDTO(Long id, Long userId, List<CartItemDTO> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }
    
    public CartDTO() {}
    
    private Long id;
    private Long userId;
    private List<CartItemDTO> items;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public List<CartItemDTO> getItems() {
        return items;
    }
    
    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return "CartDTO [id=" + id + ", userId=" + userId + ", items=" + items + "]";
    }
}