package com.LeMart.dto;

import java.util.List;

public class OrderDTO {
    public OrderDTO(Long id, Long userId, double totalAmount, String status, List<OrderItemDTO> orderItems) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderItems = orderItems;
    }
    
    public OrderDTO() {}
    
    private Long id;
    private Long userId;
    private double totalAmount;
    private String status;
    private List<OrderItemDTO> orderItems;
    
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
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }
    
    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
    
    @Override
    public String toString() {
        return "OrderDTO [id=" + id + ", userId=" + userId + ", totalAmount=" + totalAmount 
               + ", status=" + status + ", orderItems=" + orderItems + "]";
    }
}