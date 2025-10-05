package com.scinkare.repository;

import com.scinkare.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserProfileId(Long userProfileId);
    List<Goal> findByUserProfileUsername(String username);
    List<Goal> findByGoalType(String goalType);
}
