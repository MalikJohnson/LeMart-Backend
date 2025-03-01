package com.LeMart.dto;

public class CartDTO {
    public CartDTO(Long id, Long userId) {
		this.id = id;
		this.userId = userId;
	}
    public CartDTO () {}
	private Long id;
    private Long userId;
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
	@Override
	public String toString() {
		return "CartDTO [id=" + id + ", userId=" + userId + "]";
	}

}