package com.springcommerce.orderservice.dto;

public class FeignProductDTO {
	private long id;
	private String uuid;
	private String name;
	private float price;
	private int availability;
	private boolean active;

	public FeignProductDTO() {
		super();
	}

	public FeignProductDTO(long id, String uuid, String name, float price, int availability, boolean active) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.price = price;
		this.availability = availability;
		this.active = active;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
