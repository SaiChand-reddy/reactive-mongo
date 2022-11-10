package com.example.reactivemongo.service;

import com.example.reactivemongo.domain.Customer;
import com.example.reactivemongo.domain.CustomerDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Customer service component which can be auto-wired across the application to call customer related data
 */
@Service
public interface CustomerService {

    /**
     * Retrieve customer by ID
     * @param id
     * @return
     */
    Mono<Customer> getCustomerById(String id);

    /**
     * Retrieve customer contains name
     * @param name
     * @return
     */
    Flux<Customer> getCustomersContainsName(String name);

    /**
     * Save Customer based on customerDTO object
     * @param customerDTO
     * @return
     */
    Mono<Customer> saveCustomer(CustomerDTO customerDTO);
}
