package com.scinkare.repository;

import com.scinkare.model.NightRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NightRoutineRepository extends JpaRepository<NightRoutine, Long> {
    List<NightRoutine> findByUserProfileId(Long userProfileId);
    List<NightRoutine> findByUserProfileUsername(String username);
}
