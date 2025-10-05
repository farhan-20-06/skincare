package com.scinkare.controller;

import com.scinkare.model.NightRoutine;
import com.scinkare.service.NightRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/night-routines")
@CrossOrigin(origins = "*")
public class NightRoutineController {
    
    @Autowired
    private NightRoutineService nightRoutineService;
    
    @GetMapping
    public ResponseEntity<List<NightRoutine>> getAllNightRoutines() {
        List<NightRoutine> nightRoutines = nightRoutineService.getAllNightRoutines();
        return ResponseEntity.ok(nightRoutines);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NightRoutine>> getNightRoutinesByUserId(@PathVariable Long userId) {
        List<NightRoutine> nightRoutines = nightRoutineService.getNightRoutinesByUserId(userId);
        return ResponseEntity.ok(nightRoutines);
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<List<NightRoutine>> getNightRoutinesByUsername(@PathVariable String username) {
        List<NightRoutine> nightRoutines = nightRoutineService.getNightRoutinesByUsername(username);
        return ResponseEntity.ok(nightRoutines);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NightRoutine> getNightRoutineById(@PathVariable Long id) {
        Optional<NightRoutine> nightRoutine = nightRoutineService.getNightRoutineById(id);
        return nightRoutine.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/user/{userId}")
    public ResponseEntity<NightRoutine> createNightRoutine(@PathVariable Long userId, @RequestBody NightRoutine nightRoutine) {
        NightRoutine createdNightRoutine = nightRoutineService.createNightRoutine(userId, nightRoutine);
        if (createdNightRoutine != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdNightRoutine);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<NightRoutine> updateNightRoutine(@PathVariable Long id, @RequestBody NightRoutine nightRoutineDetails) {
        NightRoutine updatedNightRoutine = nightRoutineService.updateNightRoutine(id, nightRoutineDetails);
        if (updatedNightRoutine != null) {
            return ResponseEntity.ok(updatedNightRoutine);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNightRoutine(@PathVariable Long id) {
        nightRoutineService.deleteNightRoutine(id);
        return ResponseEntity.noContent().build();
    }
}
