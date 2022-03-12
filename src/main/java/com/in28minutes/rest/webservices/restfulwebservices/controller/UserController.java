package com.in28minutes.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.dao.MockDAO;
import com.in28minutes.rest.webservices.restfulwebservices.entity.User;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserController {

    MockDAO mockDAO = new MockDAO();

    @GetMapping("/csrf-token")
    public @ResponseBody String getCsrfToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        return token.getToken();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return mockDAO.getUsers();
    }

    @GetMapping("/users/{id}")
    // returning data along with links, use of HATEOAS
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = mockDAO.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("id- " + id);
        }
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(this.getClass())
                        .getUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = mockDAO.saveUser(user);

        // best practice
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        // best practice
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id) {
        User user = mockDAO.deleteUser(id);
        if (user == null) {
            throw new UserNotFoundException("id- " + id);
        }
        return user;
    }
}
