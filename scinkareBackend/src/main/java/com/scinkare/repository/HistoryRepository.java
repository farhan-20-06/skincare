package com.scinkare.repository;

import com.scinkare.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserProfileId(Long userProfileId);
    List<History> findByUserProfileUsername(String username);
    List<History> findByTableName(String tableName);
    List<History> findByAction(String action);
}
