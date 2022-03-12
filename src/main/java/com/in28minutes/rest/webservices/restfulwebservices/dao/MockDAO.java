package com.in28minutes.rest.webservices.restfulwebservices.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.in28minutes.rest.webservices.restfulwebservices.entity.User;

@Repository
public class MockDAO {

    private static int counter = 3;

    private final List<User> users;

    public MockDAO() {
        users = new ArrayList<>(Arrays.asList(new User(1, "Vyom"),
                new User(2, "Vrinda"), new User(3, "Jayant")));
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User saveUser(User user) {
        System.out.println(user);
        if (user.getId() <= 0) {
            user.setId(++counter);
            users.add(user);
        }
        else {
            for (User user1 : users) {
                if (user1.getId() == user.getId()) {
                    users.remove(user1);
                    users.add(user);
                }
            }
        }
        return user;
    }

    public User deleteUser(int id) {
        User removedUser = null;
        for (User user : users) {
            if(user.getId() == id) {
                removedUser = user;
                users.remove(user);
            }
        }
        return removedUser;
    }

}
