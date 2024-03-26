package com.springcommerce.productservice.dto;

public record CreateUpdateProductRequest(String name, float price, int availability, boolean active){}