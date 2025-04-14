package com.dharbor.sales.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewSaleDto {

    private String userId;
    private String productId;
    private Integer quantity;

}
