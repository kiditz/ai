package com.example.ai.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
class CustomerService {

	private final CustomerRepository customerRepository;


	List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	Optional<Customer> getCustomerById(Long id) {
		return customerRepository.findById(id);
	}

	Customer createCustomer(CustomerRequest request) {
		Customer customer = new Customer();
		customer.setFirstName(request.firstName());
		customer.setLastName(request.lastName());
		return customerRepository.save(customer);
	}

	Customer updateCustomer(Long id, CustomerRequest request) {
		return customerRepository.findById(id).map(customer -> {
			customer.setFirstName(request.firstName());
			customer.setLastName(request.lastName());
			return customerRepository.save(customer);
		}).orElseThrow(() -> new RuntimeException("Customer not found"));
	}

	void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
}
