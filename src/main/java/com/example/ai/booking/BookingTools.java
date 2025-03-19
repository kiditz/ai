package com.example.ai.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingTools {
	private final FlightBookingService bookingService;

	@Tool(description = "Get booking details")
	public BookingDetails getBookingDetails(String bookingNumber, String firstName, String lastName,
	                                        ToolContext toolContext) {
		try {
			return bookingService.getBookingDetails(bookingNumber, firstName, lastName);
		} catch (Exception e) {
			log.warn("Booking details: {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage());
			return new BookingDetails(bookingNumber, firstName, lastName, null, null,
					null, null, null);
		}
	}

	@Tool(description = "Change booking dates")
	public void changeBooking(String bookingNumber, String firstName, String lastName, String newDate, String from,
	                          String to) {
		bookingService.changeBooking(bookingNumber, firstName, lastName, newDate, from, to);
	}


	@Tool(description = "Cancel booking")
	public void cancelBooking(String bookingNumber, String firstName, String lastName) {
		bookingService.cancelBooking(bookingNumber, firstName, lastName);
	}


}
