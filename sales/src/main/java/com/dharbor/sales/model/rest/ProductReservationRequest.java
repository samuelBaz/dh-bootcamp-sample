package com.dharbor.sales.model.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductReservationRequest {

    private String productId;
    private Integer quantity;
}
