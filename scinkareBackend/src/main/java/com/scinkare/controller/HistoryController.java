package com.scinkare.controller;

import com.scinkare.model.History;
import com.scinkare.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
public class HistoryController {
    
    @Autowired
    private HistoryService historyService;
    
    @GetMapping
    public ResponseEntity<List<History>> getAllHistory() {
        List<History> history = historyService.getAllHistory();
        return ResponseEntity.ok(history);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<History>> getHistoryByUserId(@PathVariable Long userId) {
        List<History> history = historyService.getHistoryByUserId(userId);
        return ResponseEntity.ok(history);
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<List<History>> getHistoryByUsername(@PathVariable String username) {
        List<History> history = historyService.getHistoryByUsername(username);
        return ResponseEntity.ok(history);
    }
    
    @GetMapping("/table/{tableName}")
    public ResponseEntity<List<History>> getHistoryByTableName(@PathVariable String tableName) {
        List<History> history = historyService.getHistoryByTableName(tableName);
        return ResponseEntity.ok(history);
    }
    
    @GetMapping("/action/{action}")
    public ResponseEntity<List<History>> getHistoryByAction(@PathVariable String action) {
        List<History> history = historyService.getHistoryByAction(action);
        return ResponseEntity.ok(history);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<History> getHistoryById(@PathVariable Long id) {
        Optional<History> history = historyService.getHistoryById(id);
        return history.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<History> createHistory(@RequestBody History history) {
        History createdHistory = historyService.createHistory(history);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHistory);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long id) {
        historyService.deleteHistory(id);
        return ResponseEntity.noContent().build();
    }
}
