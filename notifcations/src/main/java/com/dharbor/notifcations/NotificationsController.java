package com.dharbor.notifcations;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationsController {

    @PostMapping("/notifications")
    public String newNotification(@RequestBody String userId) {
        return "new notification for "+userId;
    }
}
