package org.sid.billing_service.model;

import lombok.Data;

@Data
public class Product {
    private Long id ;
    private String name ;
    private Double price ;
    private Double quantity ;
}
