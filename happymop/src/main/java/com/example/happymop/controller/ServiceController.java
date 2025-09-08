package com.example.happymop.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.happymop.model.ServiceItem;
import com.example.happymop.repository.ServiceRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceRepository repo;
    public ServiceController(ServiceRepository repo){ this.repo = repo; }

    @GetMapping
    public List<ServiceItem> all(){ return repo.findAll(); }

    @PostMapping
    public ResponseEntity<ServiceItem> create(@RequestBody ServiceItem s){
        ServiceItem created = repo.save(s);
        return ResponseEntity.created(URI.create("/api/services/"+created.getId())).body(created);
    }
}
