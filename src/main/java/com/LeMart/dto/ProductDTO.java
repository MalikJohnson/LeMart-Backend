package com.LeMart.dto;

public class ProductDTO {
    public ProductDTO(Long id, String name, String description, double price, Long categoryId, String imageUrl,
			int stockQuantity, double rating) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.categoryId = categoryId;
		this.imageUrl = imageUrl;
		this.stockQuantity = stockQuantity;
		this.rating = rating;
	}
    public ProductDTO () {}
    
	private Long id;
    private String name;
    private String description;
    private double price;
    private Long categoryId;
    private String imageUrl;
    private int stockQuantity;
    private double rating;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", categoryId=" + categoryId + ", imageUrl=" + imageUrl + ", stockQuantity=" + stockQuantity
				+ ", rating=" + rating + "]";
	}
}