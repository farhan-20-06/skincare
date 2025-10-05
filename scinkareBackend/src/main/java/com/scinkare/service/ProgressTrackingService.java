package com.scinkare.service;

import com.scinkare.model.ProgressTracking;
import com.scinkare.model.UserProfile;
import com.scinkare.repository.ProgressTrackingRepository;
import com.scinkare.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressTrackingService {
    
    @Autowired
    private ProgressTrackingRepository progressTrackingRepository;
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public List<ProgressTracking> getAllProgressTracking() {
        return progressTrackingRepository.findAll();
    }
    
    public List<ProgressTracking> getProgressTrackingByUserId(Long userId) {
        return progressTrackingRepository.findByUserProfileId(userId);
    }
    
    public List<ProgressTracking> getProgressTrackingByUsername(String username) {
        return progressTrackingRepository.findByUserProfileUsername(username);
    }
    
    public Optional<ProgressTracking> getProgressTrackingById(Long id) {
        return progressTrackingRepository.findById(id);
    }
    
    public ProgressTracking createProgressTracking(Long userId, ProgressTracking progressTracking) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if (userProfile.isPresent()) {
            progressTracking.setUserProfile(userProfile.get());
            return progressTrackingRepository.save(progressTracking);
        }
        return null;
    }
    
    public ProgressTracking updateProgressTracking(Long id, ProgressTracking progressTrackingDetails) {
        Optional<ProgressTracking> optionalProgressTracking = progressTrackingRepository.findById(id);
        if (optionalProgressTracking.isPresent()) {
            ProgressTracking progressTracking = optionalProgressTracking.get();
            progressTracking.setAcneScore(progressTrackingDetails.getAcneScore());
            progressTracking.setGlowScore(progressTrackingDetails.getGlowScore());
            progressTracking.setNotes(progressTrackingDetails.getNotes());
            return progressTrackingRepository.save(progressTracking);
        }
        return null;
    }
    
    public void deleteProgressTracking(Long id) {
        progressTrackingRepository.deleteById(id);
    }
}
