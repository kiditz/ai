package com.example.ai.booking;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

interface BookingRepository extends ListCrudRepository<Booking, Long> {
	Optional<Booking> findByBookingNumberAndCustomerFirstNameAndCustomerLastName(
			String bookingNumber, String firstName, String lastName);

}
