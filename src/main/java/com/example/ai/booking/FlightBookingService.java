package com.example.ai.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
class FlightBookingService {
	private final BookingRepository bookingRepository;
	private final CustomerRepository customerRepository;

	@Transactional
	Booking addBooking(BookingRequest request) {
		Customer customer = customerRepository.findById(request.customerId())
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		Booking booking = new Booking();
		booking.setBookingNumber(request.bookingNumber());
		booking.setDate(request.date());
		booking.setBookingTo(request.bookingTo());
		booking.setCustomer(customer);
		booking.setFromLocation(request.fromLocation());
		booking.setToLocation(request.toLocation());
		booking.setBookingStatus(request.bookingStatus());
		booking.setBookingClass(request.bookingClass());
		return bookingRepository.save(booking);
	}

	List<Booking> getBookings(){
		return bookingRepository.findAll();
	}

	BookingDetails getBookingDetails(String bookingNumber, String firstName, String lastName) {
		log.info("Booking: {}, {} {}", bookingNumber, firstName, lastName);
		var booking = bookingRepository.findByBookingNumberAndCustomerFirstNameAndCustomerLastName(bookingNumber, firstName, lastName).orElseThrow(() -> new RuntimeException("booking not found"));
		return toBookingDetails(booking);
	}

	private BookingDetails toBookingDetails(Booking booking) {
		return new BookingDetails(
				booking.getBookingNumber(),
				booking.getCustomer().getFirstName(),
				booking.getCustomer().getLastName(),
				booking.getDate(),
				booking.getBookingStatus(),
				booking.getFromLocation(),
				booking.getToLocation(),
				booking.getBookingClass().toString()
		);
	}

	@Transactional
	void changeBooking(String bookingNumber, String firstName, String lastName, String newDate, String from, String to) {
		var booking = bookingRepository.findByBookingNumberAndCustomerFirstNameAndCustomerLastName(bookingNumber, firstName, lastName).orElseThrow(() -> new RuntimeException("booking not found"));
		if(booking.getDate().isBefore(LocalDate.now().plusDays(1))){
			throw new IllegalArgumentException("Booking cannot be changed within 24 hours of the start date.");
		}
		booking.setDate(LocalDate.parse(newDate));
		booking.setFromLocation(from);
		booking.setToLocation(to);
	}

	@Transactional
	public void cancelBooking(String bookingNumber, String firstName, String lastName) {
		var booking = bookingRepository.findByBookingNumberAndCustomerFirstNameAndCustomerLastName(bookingNumber, firstName, lastName).orElseThrow(() -> new RuntimeException("booking not found"));
		booking.setBookingStatus(BookingStatus.CANCELLED);
	}


}
