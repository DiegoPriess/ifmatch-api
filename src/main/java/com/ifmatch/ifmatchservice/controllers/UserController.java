package com.ifmatch.ifmatchservice.controllers;

import com.ifmatch.ifmatchservice.models.User;
import com.ifmatch.ifmatchservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    public UserController(final UserService service) {
        this.service = service;
    }

    private final UserService service;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody User user) {
        return ResponseEntity.ok(service.create(user));
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody User user) {
        return ResponseEntity.ok(service.update(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/auth/{email}/{password}")
    public ResponseEntity<User> authenticate(@PathVariable String email,
                                             @PathVariable String password) {
        return ResponseEntity.ok(service.authenticate(email, password));
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(service.list());
    }
}
