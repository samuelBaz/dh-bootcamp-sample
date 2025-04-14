package com.dharbor.sales.model.rest;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NewSaleRequest {

    private UUID userId;
    private String productId;
    private Integer quantity;


}
