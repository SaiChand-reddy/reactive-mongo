package com.example.reactivemongo.controller;

import com.example.reactivemongo.domain.Customer;
import com.example.reactivemongo.domain.CustomerDTO;
import com.example.reactivemongo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/platform/sample/mongodb")
public class WebfluxMongodbSampleController {


    @Autowired
    CustomerService customerService;

    /**
     * An endpoint to retrieve Customers based on name
     * @param name
     * @return
     */
    @GetMapping("/customers")
    public Flux<Customer> getCustomersContainName(@RequestParam(value = "name") final String name) {
        return customerService.getCustomersContainsName(name);
    }

    /**
     * An endpoint to retrieve Customer based on id
     * @param id
     * @return
     */
    @GetMapping("/customers/{id}")
    public Mono<Customer> getCustomerById(@PathVariable("id") String id) {
        return customerService.getCustomerById(id);
    }

    /**
     * An endpoint to save Customer
     * @param customerDTO
     * @return
     */
    @PostMapping("/customers")
    public Mono<Customer> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

}
