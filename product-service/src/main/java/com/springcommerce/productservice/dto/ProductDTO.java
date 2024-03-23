package com.springcommerce.productservice.dto;

public class ProductDTO {
	private String name;
	private float price;
	private int availability;
	private boolean active;
	
	
	public ProductDTO() {
		super();
	}


	public ProductDTO(long id, String name, float price, int availability, boolean active) {
		super();
		this.name = name;
		this.price = price;
		this.availability = availability;
		this.active = active;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getAvailability() {
		return availability;
	}


	public void setAvailability(int availability) {
		this.availability = availability;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
}
