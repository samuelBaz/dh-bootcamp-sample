package com.dharbor.sales.services;

import com.dharbor.sales.clients.NotificationsFeignClient;
import com.dharbor.sales.clients.RickMortyApiFeignClient;
import com.dharbor.sales.clients.StockFeignClient;
import com.dharbor.sales.clients.UsersFeignClient;
import com.dharbor.sales.exceptions.SaleNotCompletedException;
import com.dharbor.sales.model.User;
import com.dharbor.sales.model.dto.NewSaleDto;
import com.dharbor.sales.model.rest.Character;
import com.dharbor.sales.model.rest.ProductReservationRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewSalesService {

    private final UsersFeignClient usersFeignClient;

    private final StockFeignClient stockFeignClient;

    private final NotificationsFeignClient notificationsFeignClient;

    private final RickMortyApiFeignClient rickMortyApiFeignClient;

    //TODO: Apply circuit breaking and error handling for Wed 4/16/2025
    public String newSale(NewSaleDto newSaleDto) throws SaleNotCompletedException {

        User user;

        try {
            user = this.usersFeignClient.findById(newSaleDto.getUserId());
        } catch (FeignException.NotFound notFoundException) {
            throw new SaleNotCompletedException("Sale cant be completed", notFoundException);
        }

        ProductReservationRequest reservationRequest = new ProductReservationRequest();
        reservationRequest.setQuantity(newSaleDto.getQuantity());
        reservationRequest.setProductId(newSaleDto.getProductId());
        String reservationId = this.stockFeignClient.reserve(reservationRequest);

        String notification = this.notificationsFeignClient.sendNotification(newSaleDto.getUserId().toString());

        Character character = this.rickMortyApiFeignClient.findById(2);

        System.out.println(character.getName()+" "+character.getSpecies()+" "+character.getStatus());

        return "New Sale for " + user.getName() + " with reservation id " + reservationId + " and user has been notified " + notification;
    }

}
