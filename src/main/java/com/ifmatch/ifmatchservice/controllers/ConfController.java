package com.ifmatch.ifmatchservice.controllers;

import com.ifmatch.ifmatchservice.services.ConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conf")
public class ConfController {

    @Autowired
    public ConfController(final ConfService service) {
        this.service = service;
    }

    private final ConfService service;

    @PutMapping("/{flag}")
    public ResponseEntity<Object> update(@PathVariable final Boolean flag) {
        service.update(flag);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/need-update-users")
    public Boolean needUpdateUsers() {
        service.needUpdateUsers();
        return service.needUpdateUsers();
    }
}
