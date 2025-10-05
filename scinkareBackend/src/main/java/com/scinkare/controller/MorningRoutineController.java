package com.scinkare.controller;

import com.scinkare.model.MorningRoutine;
import com.scinkare.service.MorningRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/morning-routines")
@CrossOrigin(origins = "*")
public class MorningRoutineController {
    
    @Autowired
    private MorningRoutineService morningRoutineService;
    
    @GetMapping
    public ResponseEntity<List<MorningRoutine>> getAllMorningRoutines() {
        List<MorningRoutine> morningRoutines = morningRoutineService.getAllMorningRoutines();
        return ResponseEntity.ok(morningRoutines);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MorningRoutine>> getMorningRoutinesByUserId(@PathVariable Long userId) {
        List<MorningRoutine> morningRoutines = morningRoutineService.getMorningRoutinesByUserId(userId);
        return ResponseEntity.ok(morningRoutines);
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<List<MorningRoutine>> getMorningRoutinesByUsername(@PathVariable String username) {
        List<MorningRoutine> morningRoutines = morningRoutineService.getMorningRoutinesByUsername(username);
        return ResponseEntity.ok(morningRoutines);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MorningRoutine> getMorningRoutineById(@PathVariable Long id) {
        Optional<MorningRoutine> morningRoutine = morningRoutineService.getMorningRoutineById(id);
        return morningRoutine.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/user/{userId}")
    public ResponseEntity<MorningRoutine> createMorningRoutine(@PathVariable Long userId, @RequestBody MorningRoutine morningRoutine) {
        MorningRoutine createdMorningRoutine = morningRoutineService.createMorningRoutine(userId, morningRoutine);
        if (createdMorningRoutine != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMorningRoutine);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MorningRoutine> updateMorningRoutine(@PathVariable Long id, @RequestBody MorningRoutine morningRoutineDetails) {
        MorningRoutine updatedMorningRoutine = morningRoutineService.updateMorningRoutine(id, morningRoutineDetails);
        if (updatedMorningRoutine != null) {
            return ResponseEntity.ok(updatedMorningRoutine);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMorningRoutine(@PathVariable Long id) {
        morningRoutineService.deleteMorningRoutine(id);
        return ResponseEntity.noContent().build();
    }
}
