package com.LeMart.dto;

public class OrderDTO {
    public OrderDTO(Long id, Long userId, double totalAmount, String status) {
		this.id = id;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.status = status;
	}
    public OrderDTO () {}
	private Long id;
    private Long userId;
    private double totalAmount;
    private String status;
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
	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", userId=" + userId + ", totalAmount=" + totalAmount + ", status=" + status
				+ "]";
	}
}