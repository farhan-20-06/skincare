package com.scinkare.service;

import com.scinkare.model.NightRoutine;
import com.scinkare.model.UserProfile;
import com.scinkare.repository.NightRoutineRepository;
import com.scinkare.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NightRoutineService {
    
    @Autowired
    private NightRoutineRepository nightRoutineRepository;
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public List<NightRoutine> getAllNightRoutines() {
        return nightRoutineRepository.findAll();
    }
    
    public List<NightRoutine> getNightRoutinesByUserId(Long userId) {
        return nightRoutineRepository.findByUserProfileId(userId);
    }
    
    public List<NightRoutine> getNightRoutinesByUsername(String username) {
        return nightRoutineRepository.findByUserProfileUsername(username);
    }
    
    public Optional<NightRoutine> getNightRoutineById(Long id) {
        return nightRoutineRepository.findById(id);
    }
    
    public NightRoutine createNightRoutine(Long userId, NightRoutine nightRoutine) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if (userProfile.isPresent()) {
            nightRoutine.setUserProfile(userProfile.get());
            return nightRoutineRepository.save(nightRoutine);
        }
        return null;
    }
    
    public NightRoutine updateNightRoutine(Long id, NightRoutine nightRoutineDetails) {
        Optional<NightRoutine> optionalNightRoutine = nightRoutineRepository.findById(id);
        if (optionalNightRoutine.isPresent()) {
            NightRoutine nightRoutine = optionalNightRoutine.get();
            nightRoutine.setProductName(nightRoutineDetails.getProductName());
            nightRoutine.setBrand(nightRoutineDetails.getBrand());
            nightRoutine.setCategory(nightRoutineDetails.getCategory());
            nightRoutine.setNotes(nightRoutineDetails.getNotes());
            return nightRoutineRepository.save(nightRoutine);
        }
        return null;
    }
    
    public void deleteNightRoutine(Long id) {
        nightRoutineRepository.deleteById(id);
    }
}
