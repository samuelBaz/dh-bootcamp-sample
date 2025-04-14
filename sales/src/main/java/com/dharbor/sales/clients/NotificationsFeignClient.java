package com.dharbor.sales.clients;

import com.dharbor.sales.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notifications-service", configuration = FeignClientConfig.class)
public interface NotificationsFeignClient {

    @PostMapping("/notifications")
    String sendNotification(@RequestBody String userId);
}
