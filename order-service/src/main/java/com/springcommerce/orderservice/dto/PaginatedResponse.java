package com.springcommerce.orderservice.dto;


import java.util.List;

public class PaginatedResponse<T> {
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private List<T> items;
    

	public PaginatedResponse() {
		super();
	}
	
	public PaginatedResponse(int currentPage, int totalPages, long totalItems, List<T> items) {
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
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
}