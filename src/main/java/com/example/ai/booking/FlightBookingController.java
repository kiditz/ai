package com.example.ai.booking;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Booking")
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
class FlightBookingController {

	private final FlightBookingService service;


	@PostMapping
	public ResponseEntity<Booking> addBooking(@Valid @RequestBody BookingRequest request) {
		return ResponseEntity.ok(service.addBooking(request));
	}

	@GetMapping
	public ResponseEntity<List<Booking>> getBookings() {
		return ResponseEntity.ok(service.getBookings());
	}
}
