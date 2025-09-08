package com.example.happymop.controller;

import com.example.happymop.model.WorkerApplication;
import com.example.happymop.model.Worker;
import com.example.happymop.service.WorkerApplicationService;
import com.example.happymop.service.WorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final WorkerApplicationService appSvc;
    private final WorkerService workerSvc;
    public AdminController(WorkerApplicationService appSvc, WorkerService workerSvc){ this.appSvc = appSvc; this.workerSvc = workerSvc; }

    @GetMapping("/applications")
    public List<WorkerApplication> apps(){ return appSvc.all(); }

    @PostMapping("/applications")
    public ResponseEntity<WorkerApplication> createApp(@RequestBody WorkerApplication a){ WorkerApplication created = appSvc.create(a); return ResponseEntity.created(URI.create("/api/admin/applications/"+created.getId())).body(created); }

    @PostMapping("/applications/{id}/approve")
    public ResponseEntity<Worker> approve(@PathVariable Long id){
        WorkerApplication app = appSvc.find(id).orElseThrow();
        // create worker
        Worker w = new Worker();
        w.setName(app.getName()); w.setPhone(app.getPhone()); w.setServices(app.getServices()); w.setArea(app.getArea());
        Worker created = workerSvc.create(w);
        app.setStatus("approved"); appSvc.create(app);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/applications/{id}/reject")
    public ResponseEntity<Void> reject(@PathVariable Long id){ appSvc.delete(id); return ResponseEntity.noContent().build(); }
}