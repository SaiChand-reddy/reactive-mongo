package com.example.reactivemongo.repository;

import com.example.reactivemongo.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Customer reactive mongodb repository to handle operation like CRUD etc..
 */
@Repository
public interface CustomerReactiveRepository extends ReactiveMongoRepository<Customer, String> {

    Flux<Customer> findByFirstNameLike(String name);
}
