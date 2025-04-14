package com.dharbor.users.repositories;

import com.dharbor.users.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRepository {

    private Map<UUID, User> store;

    @PostConstruct()
    public void initStore() {
        this.store = new HashMap<>();
    }

    public User create(User user) {
        user.setId(UUID.randomUUID());
        this.store.put(user.getId(), user);

        return user;
    }

    public Collection<User> getAll() {
        return this.store.values();
    }

    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(this.store.get(id));
    }
}
