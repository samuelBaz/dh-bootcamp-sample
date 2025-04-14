package com.dharbor.notifcations;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationsController {

    @PostMapping("/notifications")
    public String newNotification(@RequestBody String userId, HttpServletRequest request) {

        System.out.println(request.getHeader("X-TENANT-ID"));
        System.out.println(request.getHeader("X-CLIENT-ID"));

        return "new notification for "+userId;
    }
}
