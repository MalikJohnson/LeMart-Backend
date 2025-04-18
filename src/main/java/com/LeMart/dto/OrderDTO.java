package com.LeMart.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    public OrderDTO(Long id, Long userId, double subtotal, double tax, double shipping, 
                   double totalAmount, String status, Date createdAt, List<OrderItemDTO> orderItems) {
        this.id = id;
        this.userId = userId;
        this.subtotal = subtotal;
        this.tax = tax;
        this.shipping = shipping;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.orderItems = orderItems;
    }
    
    public OrderDTO() {}
    
    private Long id;
    private Long userId;
    private double subtotal;
    private double tax;
    private double shipping;
    private double totalAmount;
    private String status;
    private Date createdAt;
    private List<OrderItemDTO> orderItems;
    
    // Getters and Setters
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
    
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
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
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }
    
    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
    
    @Override
    public String toString() {
        return "OrderDTO [id=" + id + 
               ", userId=" + userId + 
               ", subtotal=" + subtotal + 
               ", tax=" + tax + 
               ", shipping=" + shipping + 
               ", totalAmount=" + totalAmount + 
               ", status=" + status + 
               ", createdAt=" + createdAt + 
               ", orderItems=" + orderItems + "]";
    }
}