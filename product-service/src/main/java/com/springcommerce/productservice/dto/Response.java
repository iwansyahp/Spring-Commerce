package com.springcommerce.productservice.dto;

public record Response<T>(T data, String message) {
	public Response(String message) {
		this(null, message);
	}
	public Response(T data) {
		this(data, null);
	}
}