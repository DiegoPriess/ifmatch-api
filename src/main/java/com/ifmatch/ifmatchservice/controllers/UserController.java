package com.ifmatch.ifmatchservice.controllers;

import com.ifmatch.ifmatchservice.enums.UserStatus;
import com.ifmatch.ifmatchservice.models.User;
import com.ifmatch.ifmatchservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserController(final UserService service) {
        this.service = service;
    }

    private final UserService service;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody User users) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(users));
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody User users) {
        return ResponseEntity.ok(service.update(users));
    }

    @GetMapping("/change-status/{id}/{status}")
    public ResponseEntity changeStatus(@PathVariable final Long id,
                                       @PathVariable final UserStatus status) {
        service.changeStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Optional<User>> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.getByEmail(email));
    }

    @GetMapping
    public List<User> list() {
        return service.list();
    }

    @GetMapping("/{page}/{size}")
    public Page<User> listPage(@PathVariable Integer page,
                               @PathVariable Integer size) {
        return service.listPage(page, size);
    }
}
