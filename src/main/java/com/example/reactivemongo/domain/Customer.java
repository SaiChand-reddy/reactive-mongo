package com.example.reactivemongo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Simple POJO class to demonstrate as Document to be used in MongoDB sample project
 * which include properties like id, firstName & lastName
 */
@Data
@Document
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
}
