package com.dharbor.sales.clients;

import com.dharbor.sales.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "users-service", url = "http://localhost:8080/users-service")
public interface UsersFeignClient {

    @GetMapping("/users/{id}")
    User findById(@PathVariable UUID id);

}
