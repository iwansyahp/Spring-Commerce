package com.springcommerce.userservice.dto;

import java.util.UUID;

public record UserDTO(UUID uuid, String email, String firstName, String lastName) {}
