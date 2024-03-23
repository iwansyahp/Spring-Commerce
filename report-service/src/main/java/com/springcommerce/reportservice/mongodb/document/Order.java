package com.springcommerce.reportservice.mongodb.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {

        @Id
        private String id;
        
    	private String orderName;
    	
    	private Float totalPay;
    	
    	private String status;
    	
    	private Date createdAt;

    	private Date updatedAt;

		public Order(String id, String orderName, Float totalPay, String status, Date createdAt, Date updatedAt) {
			super();
			this.id = id;
			this.orderName = orderName;
			this.totalPay = totalPay;
			this.status = status;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getOrderName() {
			return orderName;
		}

		public void setOrderName(String orderName) {
			this.orderName = orderName;
		}

		public Float getTotalPay() {
			return totalPay;
		}

		public void setTotalPay(Float totalPay) {
			this.totalPay = totalPay;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
}