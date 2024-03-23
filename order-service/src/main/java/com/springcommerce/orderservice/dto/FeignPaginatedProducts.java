package com.springcommerce.orderservice.dto;

import java.util.ArrayList;
import java.util.List;

public class FeignPaginatedProducts {
	private int currentPage;
	private int totalPages;
	private int totalItems;
	private List<FeignProductDTO> items = new ArrayList<>();


	public FeignPaginatedProducts() {
		super();
	}
	public FeignPaginatedProducts(int currentPage, int totalPages, int totalItems, List<FeignProductDTO> items) {
		super();
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.totalItems = totalItems;
		this.items = items;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public List<FeignProductDTO> getItems() {
		return items;
	}
	public void setItems(List<FeignProductDTO> items) {
		this.items = items;
	}

}
