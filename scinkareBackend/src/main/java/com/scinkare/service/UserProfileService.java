package com.scinkare.service;

import com.scinkare.model.UserProfile;
import com.scinkare.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }
    
    public Optional<UserProfile> getUserProfileById(Long id) {
        return userProfileRepository.findById(id);
    }
    
    public Optional<UserProfile> getUserProfileByUsername(String username) {
        return userProfileRepository.findByUsername(username);
    }
    
    public UserProfile createUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }
    
    public UserProfile updateUserProfile(Long id, UserProfile userProfileDetails) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(id);
        if (optionalUserProfile.isPresent()) {
            UserProfile userProfile = optionalUserProfile.get();
            userProfile.setUsername(userProfileDetails.getUsername());
            userProfile.setSkinType(userProfileDetails.getSkinType());
            return userProfileRepository.save(userProfile);
        }
        return null;
    }
    
    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }
    
    public boolean existsByUsername(String username) {
        return userProfileRepository.existsByUsername(username);
    }
}
