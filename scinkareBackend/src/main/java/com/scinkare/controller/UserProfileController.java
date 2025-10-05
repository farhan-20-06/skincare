package com.scinkare.controller;

import com.scinkare.model.UserProfile;
import com.scinkare.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-profiles")
@CrossOrigin(origins = "*")
public class UserProfileController {
    
    @Autowired
    private UserProfileService userProfileService;
    
    @GetMapping
    public ResponseEntity<List<UserProfile>> getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileService.getAllUserProfiles();
        return ResponseEntity.ok(userProfiles);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable Long id) {
        Optional<UserProfile> userProfile = userProfileService.getUserProfileById(id);
        return userProfile.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<UserProfile> getUserProfileByUsername(@PathVariable String username) {
        Optional<UserProfile> userProfile = userProfileService.getUserProfileByUsername(username);
        return userProfile.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile) {
        if (userProfileService.existsByUsername(userProfile.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        UserProfile createdUserProfile = userProfileService.createUserProfile(userProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserProfile);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable Long id, @RequestBody UserProfile userProfileDetails) {
        UserProfile updatedUserProfile = userProfileService.updateUserProfile(id, userProfileDetails);
        if (updatedUserProfile != null) {
            return ResponseEntity.ok(updatedUserProfile);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return ResponseEntity.noContent().build();
    }
}
