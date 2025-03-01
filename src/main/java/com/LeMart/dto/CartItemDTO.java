package com.LeMart.dto;

public class CartItemDTO {
    public CartItemDTO(Long id, Long cartId, Long productId, int quantity) {
		this.id = id;
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
	}
    public CartItemDTO() {}
	private Long id;
    private Long cartId;
    private Long productId;
    private int quantity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
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
	@Override
	public String toString() {
		return "CartItemDTO [id=" + id + ", cartId=" + cartId + ", productId=" + productId + ", quantity=" + quantity
				+ "]";
	}

}