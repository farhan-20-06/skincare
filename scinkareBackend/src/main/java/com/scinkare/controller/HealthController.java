package com.scinkare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
@CrossOrigin(origins = "*")
public class HealthController {

    private final DataSource dataSource;

    public HealthController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> body = new HashMap<>();
        body.put("app", "ok");

        try (Connection ignored = dataSource.getConnection()) {
            body.put("database", "connected");
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("database", "error");
            body.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(body);
        }
    }
}


