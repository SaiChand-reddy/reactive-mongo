package com.example.reactivemongo.service.impl;

import com.example.reactivemongo.domain.Customer;
import com.example.reactivemongo.domain.CustomerDTO;
import com.example.reactivemongo.repository.CustomerReactiveRepository;
import com.example.reactivemongo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Customer service component which can be auto-wired across the application to call customer related data
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerReactiveRepository customerReactiveRepository;

    @Autowired
    ReactiveMongoOperations reactiveMongoOperations;

    /**
     * Retrieve customer by ID
     * @param id
     * @return
     */
    @Override
    public Mono<Customer> getCustomerById(String id) {
        return customerReactiveRepository.findById(id);
    }

    /**
     * Retrieve customer contains name
     * @param name
     * @return
     */
    @Override
    public Flux<Customer> getCustomersContainsName(String name) {
        return customerReactiveRepository.findByFirstNameLike(name);
    }

    /**
     * Save customer
     * @param customerDTO
     * @return
     */
    @Override
    public Mono<Customer> saveCustomer(CustomerDTO customerDTO) {
//        We can have DTO layer to transfer data, but for sample we will do the following
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        return customerReactiveRepository.save(customer);
    }
}
