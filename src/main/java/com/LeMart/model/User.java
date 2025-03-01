package com.LeMart.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
public class User {
    public User(Long id, String username, String email, String passwordHash, String streetAddress, String city,
			String state, String zipCode, boolean isAdmin, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.passwordHash = passwordHash;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.isAdmin = isAdmin;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
    public User() {}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(name = "street_address")
    private String streetAddress; // Street address (e.g., "123 Main St")

    @Column(nullable = false)
    private String city; // City (e.g., "New York")

    @Column(nullable = false)
    private String state; // State (e.g., "NY")

    @Column(name = "zip_code", nullable = false)
    private String zipCode; // Zip code (e.g., "10001")

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
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
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", passwordHash=" + passwordHash
				+ ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode
				+ ", isAdmin=" + isAdmin + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}