package com.in28minutes.rest.webservices.restfulwebservices.entity;

import java.util.Objects;

import javax.validation.constraints.Size;

public class User {

    @Size(min = 2, message = "Name should be more than 2 characters")
    private String name;

    private int id;

    public User() {
    }

    public User(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
