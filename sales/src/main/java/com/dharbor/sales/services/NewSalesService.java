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
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewSalesService {

    private final UsersFeignClient usersFeignClient;

    private final StockFeignClient stockFeignClient;

    private final NotificationsFeignClient notificationsFeignClient;

    private final RickMortyApiFeignClient rickMortyApiFeignClient;

    //TODO: Apply circuit breaking and error handling for Wed 4/16/2025
    public String newSale(NewSaleDto newSaleDto) throws SaleNotCompletedException {

        User user = getUserOrThrow(newSaleDto.getUserId());
        String reservationId = reserveProduct(newSaleDto);
        String notification = sendNotificationToUser(user);
        Character character = this.rickMortyApiFeignClient.findById(2);

        System.out.println(character.getName()+" "+character.getSpecies()+" "+character.getStatus());

        return "New Sale for " + user.getName() + " with reservation id " + reservationId + " and user has been notified " + notification;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetUser")
    private User getUserOrThrow(UUID userId) {
        try {
            User user = usersFeignClient.findById(userId);
            if (user == null) {
                throw new SaleNotCompletedException("User not found, sale can't be completed.");
            }
            return user;
        } catch (FeignException.NotFound e) {
            throw new SaleNotCompletedException("User not found in remote service.", e);
        } catch (Exception e) {
            throw new SaleNotCompletedException("Error fetching user data.", e);
        }
    }

    public User fallbackGetUser(Long userId, Throwable throwable) {
        throw new SaleNotCompletedException("User service is currently unavailable. Please try again later.", throwable);
    }

    private String reserveProduct(NewSaleDto dto) {
        try {
            ProductReservationRequest request = ProductReservationRequest.builder()
                    .productId(dto.getProductId())
                    .quantity(dto.getQuantity())
                    .build();
            return stockFeignClient.reserve(request);
        } catch (FeignException e) {
            throw new SaleNotCompletedException("Error while reserving product stock.", e);
        }
    }

    private String sendNotificationToUser(User user) {
        try {
            return notificationsFeignClient.sendNotification(user.getId().toString());
        } catch (FeignException e) {
            throw new SaleNotCompletedException("Failed to send notification to user.", e);
        }
    }

}
