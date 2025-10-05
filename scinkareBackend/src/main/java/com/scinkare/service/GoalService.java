package com.scinkare.service;

import com.scinkare.model.Goal;
import com.scinkare.model.UserProfile;
import com.scinkare.repository.GoalRepository;
import com.scinkare.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GoalService {
    
    @Autowired
    private GoalRepository goalRepository;
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }
    
    public List<Goal> getGoalsByUserId(Long userId) {
        return goalRepository.findByUserProfileId(userId);
    }
    
    public List<Goal> getGoalsByUsername(String username) {
        return goalRepository.findByUserProfileUsername(username);
    }
    
    public List<Goal> getGoalsByType(String goalType) {
        return goalRepository.findByGoalType(goalType);
    }
    
    public Optional<Goal> getGoalById(Long id) {
        return goalRepository.findById(id);
    }
    
    public Goal createGoal(Long userId, Goal goal) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if (userProfile.isPresent()) {
            goal.setUserProfile(userProfile.get());
            return goalRepository.save(goal);
        }
        return null;
    }
    
    public Goal updateGoal(Long id, Goal goalDetails) {
        Optional<Goal> optionalGoal = goalRepository.findById(id);
        if (optionalGoal.isPresent()) {
            Goal goal = optionalGoal.get();
            goal.setGoalTitle(goalDetails.getGoalTitle());
            goal.setGoalType(goalDetails.getGoalType());
            goal.setDateEntry(goalDetails.getDateEntry());
            goal.setGoalDetail(goalDetails.getGoalDetail());
            return goalRepository.save(goal);
        }
        return null;
    }
    
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }
}
