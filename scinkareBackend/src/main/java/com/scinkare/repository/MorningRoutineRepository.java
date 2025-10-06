package com.scinkare.repository;

import com.scinkare.model.MorningRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MorningRoutineRepository extends JpaRepository<MorningRoutine, Long> {
    @Query("SELECT mr FROM MorningRoutine mr WHERE mr.userProfile.id = :userId")
    List<MorningRoutine> findByUserProfileId(@Param("userId") Long userProfileId);
    
    @Query("SELECT mr FROM MorningRoutine mr WHERE mr.userProfile.username = :username")
    List<MorningRoutine> findByUserProfileUsername(@Param("username") String username);
}
