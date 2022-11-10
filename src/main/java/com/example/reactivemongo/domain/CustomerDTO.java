package com.example.reactivemongo.domain;

import lombok.Data;

/**
 * Simple DTO class of Customer class to be used in MongoDB sample project
 * which include properties like id, firstName & lastName
 */
@Data
public class CustomerDTO {

    private String id;
    private String firstName;
    private String lastName;
}
