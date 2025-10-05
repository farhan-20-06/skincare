package com.scinkare.service;

import com.scinkare.model.MorningRoutine;
import com.scinkare.model.UserProfile;
import com.scinkare.repository.MorningRoutineRepository;
import com.scinkare.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MorningRoutineService {
    
    @Autowired
    private MorningRoutineRepository morningRoutineRepository;
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public List<MorningRoutine> getAllMorningRoutines() {
        return morningRoutineRepository.findAll();
    }
    
    public List<MorningRoutine> getMorningRoutinesByUserId(Long userId) {
        return morningRoutineRepository.findByUserProfileId(userId);
    }
    
    public List<MorningRoutine> getMorningRoutinesByUsername(String username) {
        return morningRoutineRepository.findByUserProfileUsername(username);
    }
    
    public Optional<MorningRoutine> getMorningRoutineById(Long id) {
        return morningRoutineRepository.findById(id);
    }
    
    public MorningRoutine createMorningRoutine(Long userId, MorningRoutine morningRoutine) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if (userProfile.isPresent()) {
            morningRoutine.setUserProfile(userProfile.get());
            return morningRoutineRepository.save(morningRoutine);
        }
        return null;
    }
    
    public MorningRoutine updateMorningRoutine(Long id, MorningRoutine morningRoutineDetails) {
        Optional<MorningRoutine> optionalMorningRoutine = morningRoutineRepository.findById(id);
        if (optionalMorningRoutine.isPresent()) {
            MorningRoutine morningRoutine = optionalMorningRoutine.get();
            morningRoutine.setProductName(morningRoutineDetails.getProductName());
            morningRoutine.setBrand(morningRoutineDetails.getBrand());
            morningRoutine.setCategory(morningRoutineDetails.getCategory());
            morningRoutine.setNotes(morningRoutineDetails.getNotes());
            return morningRoutineRepository.save(morningRoutine);
        }
        return null;
    }
    
    public void deleteMorningRoutine(Long id) {
        morningRoutineRepository.deleteById(id);
    }
}
