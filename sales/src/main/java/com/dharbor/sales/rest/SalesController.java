package com.dharbor.sales.rest;

import com.dharbor.sales.exceptions.SaleNotCompletedException;
import com.dharbor.sales.model.dto.NewSaleDto;
import com.dharbor.sales.model.rest.NewSaleRequest;
import com.dharbor.sales.services.NewSalesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class SalesController {

    private final NewSalesService newSalesService;

    public SalesController(NewSalesService newSalesService) {
        this.newSalesService = newSalesService;
    }

    @PostMapping("/sales/newSale")
    public ResponseEntity<String> newSale(@RequestBody NewSaleRequest newSaleRequest) {

        try {
            NewSaleDto newSaleDto = new NewSaleDto();
            newSaleDto.setUserId(newSaleRequest.getUserId());
            newSaleDto.setProductId(newSaleRequest.getProductId());
            newSaleDto.setQuantity(newSaleRequest.getQuantity());

            return  ResponseEntity.ok(this.newSalesService.newSale(newSaleDto));
        } catch (SaleNotCompletedException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }

    }

}
