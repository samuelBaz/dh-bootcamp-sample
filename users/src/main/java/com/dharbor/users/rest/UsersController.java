package com.dharbor.users.rest;

import com.dharbor.users.exceptions.UserNotFoundException;
import com.dharbor.users.model.User;
import com.dharbor.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@Component
@RestController()
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public Collection<User> getUsers() {
        return usersService.getAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {

        try {
            User user = this.usersService.findById(id);

            return ResponseEntity.ok(user);

        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }

    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        return this.usersService.create(user);
    }

    @PostMapping("/users/bulk")
    public Collection<User> bulkCreate(@RequestBody Collection<User> users) {
        return this.usersService.bulkCreate(users);
    }
}
