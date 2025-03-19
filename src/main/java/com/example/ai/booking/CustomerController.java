package com.example.ai.booking;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customers")
class CustomerController {

	private final CustomerService customerService;

	CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@GetMapping("/{id}")
	ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerRequest request) {
		return ResponseEntity.ok(customerService.createCustomer(request));
	}

	@PutMapping("/{id}")
	ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
		return ResponseEntity.ok(customerService.updateCustomer(id, request));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}
}
