package com.dharbor.stock.rest;

import com.dharbor.stock.model.rest.ProductReservationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
public class ProductsController {

    @PostMapping("/products/reserve")
    public String reserve(@RequestBody ProductReservationRequest request) {
        System.out.println("Reservation created for "+request.getProductId());

        return UUID.randomUUID().toString();
    }

}
