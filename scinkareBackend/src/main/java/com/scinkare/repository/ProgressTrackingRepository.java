package com.scinkare.repository;

import com.scinkare.model.ProgressTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProgressTrackingRepository extends JpaRepository<ProgressTracking, Long> {
    List<ProgressTracking> findByUserProfileId(Long userProfileId);
    List<ProgressTracking> findByUserProfileUsername(String username);
}
