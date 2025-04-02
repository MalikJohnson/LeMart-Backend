package com.LeMart.dto;

public class OrderItemDTO {
    public OrderItemDTO(Long id, Long orderId, Long productId, int quantity, 
                      double priceAtPurchase, String productName, String productImage) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
        this.productName = productName;
        this.productImage = productImage;
    }
    
    public OrderItemDTO() {}
    
    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;
    private double priceAtPurchase;
    private String productName;
    private String productImage;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }
    
    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductImage() {
        return productImage;
    }
    
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    
    @Override
    public String toString() {
        return "OrderItemDTO [id=" + id + 
               ", orderId=" + orderId + 
               ", productId=" + productId + 
               ", quantity=" + quantity + 
               ", priceAtPurchase=" + priceAtPurchase + 
               ", productName=" + productName + 
               ", productImage=" + productImage + "]";
    }
}