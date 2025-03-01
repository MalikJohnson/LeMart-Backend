package com.LeMart.dto;

public class OrderItemDTO {
    public OrderItemDTO(Long id, Long orderId, Long productId, int quantity, double priceAtPurchase) {
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.priceAtPurchase = priceAtPurchase;
	}
    public OrderItemDTO (){}
    
	private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;
    private double priceAtPurchase;
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
	@Override
	public String toString() {
		return "OrderItemDTO [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity
				+ ", priceAtPurchase=" + priceAtPurchase + "]";
	}

}