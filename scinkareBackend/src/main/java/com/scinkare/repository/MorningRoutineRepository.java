package com.scinkare.repository;

import com.scinkare.model.MorningRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MorningRoutineRepository extends JpaRepository<MorningRoutine, Long> {
    List<MorningRoutine> findByUserProfileId(Long userProfileId);
    List<MorningRoutine> findByUserProfileUsername(String username);
}
