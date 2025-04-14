package com.dharbor.users.services;

import com.dharbor.users.exceptions.UserNotFoundException;
import com.dharbor.users.model.User;
import com.dharbor.users.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UsersService {

    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return this.userRepository.create(user);
    }

    public Collection<User> bulkCreate(Collection<User> users) {
        Collection<User> result = new ArrayList<>();

        users.forEach( user -> {
            result.add(this.create(user));
        });

        return result;
    }

    public Collection<User> getAll() {
        return this.userRepository.getAll();
    }

    public User findById(UUID id) throws UserNotFoundException {
        Optional<User> userOptional = this.userRepository.findById(id);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new UserNotFoundException("User not found for id "+id);
    }

}
