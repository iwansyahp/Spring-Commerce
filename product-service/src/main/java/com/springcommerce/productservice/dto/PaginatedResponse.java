package com.springcommerce.productservice.dto;

import java.util.List;

public record PaginatedResponse<T>(int currentPage, int totalPages, long totalData, List<T> data){}