package com.springcommerce.orderservice.entity;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

/** this entity is owned by product service, this service can only read them and will be synchronized via kafka */

@SuppressWarnings("deprecation")
@Entity
@Table(indexes = @Index(columnList = "name"))
@Where(clause = "deleted = false")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private UUID uuid;
	
	private String name;
	
	private Float price;
	
	private Integer availability;
	
	@Column(nullable = false)
	private boolean active = true;
	
	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	private boolean deleted = false;
	
	public Product(String name, Float price, Integer availability) {
		super();
		this.name = name;
		this.price = price;
		this.availability = availability;
	}


	public Product(UUID uuid, String name, Float price, Integer availability, boolean active, Date createdAt,
			Date updatedAt) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.price = price;
		this.availability = availability;
		this.active = active;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Product() {
		super();
	}

	public Long getId() {
		return id;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public Float getPrice() {
		return price;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}
}
