package com.example.happymop.controller;

import com.example.happymop.model.User;
import com.example.happymop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService svc;
    public UserController(UserService svc){ this.svc = svc; }

    @GetMapping
    public List<User> all(){ return svc.all(); }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User u){
        User created = svc.create(u);
        return ResponseEntity.created(URI.create("/api/users/"+created.getId())).body(created);
    }
}

