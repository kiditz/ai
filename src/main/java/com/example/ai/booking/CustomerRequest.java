package com.example.ai.booking;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
    @NotBlank String firstName,
    @NotBlank String lastName
) {}
