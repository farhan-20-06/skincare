package com.scinkare.controller;

import com.scinkare.model.ProgressTracking;
import com.scinkare.service.ProgressTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/progress-tracking")
@CrossOrigin(origins = "*")
public class ProgressTrackingController {
    
    @Autowired
    private ProgressTrackingService progressTrackingService;
    
    @GetMapping
    public ResponseEntity<List<ProgressTracking>> getAllProgressTracking() {
        List<ProgressTracking> progressTracking = progressTrackingService.getAllProgressTracking();
        return ResponseEntity.ok(progressTracking);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProgressTracking>> getProgressTrackingByUserId(@PathVariable Long userId) {
        List<ProgressTracking> progressTracking = progressTrackingService.getProgressTrackingByUserId(userId);
        return ResponseEntity.ok(progressTracking);
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<List<ProgressTracking>> getProgressTrackingByUsername(@PathVariable String username) {
        List<ProgressTracking> progressTracking = progressTrackingService.getProgressTrackingByUsername(username);
        return ResponseEntity.ok(progressTracking);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProgressTracking> getProgressTrackingById(@PathVariable Long id) {
        Optional<ProgressTracking> progressTracking = progressTrackingService.getProgressTrackingById(id);
        return progressTracking.map(ResponseEntity::ok)
                              .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/user/{userId}")
    public ResponseEntity<ProgressTracking> createProgressTracking(@PathVariable Long userId, @RequestBody ProgressTracking progressTracking) {
        ProgressTracking createdProgressTracking = progressTrackingService.createProgressTracking(userId, progressTracking);
        if (createdProgressTracking != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProgressTracking);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProgressTracking> updateProgressTracking(@PathVariable Long id, @RequestBody ProgressTracking progressTrackingDetails) {
        ProgressTracking updatedProgressTracking = progressTrackingService.updateProgressTracking(id, progressTrackingDetails);
        if (updatedProgressTracking != null) {
            return ResponseEntity.ok(updatedProgressTracking);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressTracking(@PathVariable Long id) {
        progressTrackingService.deleteProgressTracking(id);
        return ResponseEntity.noContent().build();
    }
}
