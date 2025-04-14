package com.dharbor.sales.services;

import com.dharbor.sales.model.dto.NewSaleDto;
import org.springframework.stereotype.Service;

@Service
public class NewSalesService {

    public String newSale(NewSaleDto newSaleDto) {
        return "New Sale "+newSaleDto.getUserId();
    }

}
