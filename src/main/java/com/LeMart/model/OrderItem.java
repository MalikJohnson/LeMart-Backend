package com.LeMart.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "order_items")
public class OrderItem {
    public OrderItem(Long id, Order order, Product product, int quantity, double priceAtPurchase,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.priceAtPurchase = priceAtPurchase;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
    public OrderItem() {}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "price_at_purchase", nullable = false)
    private double priceAtPurchase;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", order=" + order + ", product=" + product + ", quantity=" + quantity
				+ ", priceAtPurchase=" + priceAtPurchase + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}
}