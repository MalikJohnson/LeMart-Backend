package com.LeMart.dto;

public class UserDTO {
    public UserDTO(Long id, String username, String email, String streetAddress, String city, String state,
			String zipCode, boolean isAdmin) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.isAdmin = isAdmin;
	}
    public UserDTO (){}
    
	private Long id;
    private String username;
    private String email;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private boolean isAdmin;
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
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", email=" + email + ", streetAddress=" + streetAddress
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", isAdmin=" + isAdmin + "]";
	}

}