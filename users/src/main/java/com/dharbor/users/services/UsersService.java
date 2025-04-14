package com.dharbor.users.services;

import com.dharbor.users.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UsersService {

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

    public Collection<User> bulkCreate(Collection<User> users) {
        Collection<User> result = new ArrayList<>();

        users.forEach( user -> {
            result.add(this.create(user));
        });

        return result;
    }

    public Collection<User> getAll() {
        return this.store.values();
    }

    public User findById(UUID id) {
        return this.store.get(id);
    }

}
