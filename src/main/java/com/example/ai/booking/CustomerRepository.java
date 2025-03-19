package com.example.ai.booking;

import org.springframework.data.repository.ListCrudRepository;

interface CustomerRepository extends ListCrudRepository<Customer, Long> {

}
