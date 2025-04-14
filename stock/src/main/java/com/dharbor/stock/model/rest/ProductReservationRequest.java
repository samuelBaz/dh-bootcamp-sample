package com.dharbor.stock.model.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReservationRequest {

    private String productId;
    private Integer quantity;
}
