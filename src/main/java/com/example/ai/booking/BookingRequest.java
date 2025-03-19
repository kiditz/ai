package com.example.ai.booking;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record BookingRequest(
    @NotBlank String bookingNumber,
    @NotNull LocalDate date,
    @NotNull LocalDate bookingTo,
    @NotNull Long customerId, // Reference to Customer
    @NotBlank String fromLocation,
    @NotBlank String toLocation,
    @NotNull BookingStatus bookingStatus,
    @NotNull BookingClass bookingClass
) {}
