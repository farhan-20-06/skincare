package com.scinkare.service;

import com.scinkare.model.History;
import com.scinkare.model.UserProfile;
import com.scinkare.repository.HistoryRepository;
import com.scinkare.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    
    @Autowired
    private HistoryRepository historyRepository;
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }
    
    public List<History> getHistoryByUserId(Long userId) {
        return historyRepository.findByUserProfileId(userId);
    }
    
    public List<History> getHistoryByUsername(String username) {
        return historyRepository.findByUserProfileUsername(username);
    }
    
    public List<History> getHistoryByTableName(String tableName) {
        return historyRepository.findByTableName(tableName);
    }
    
    public List<History> getHistoryByAction(String action) {
        return historyRepository.findByAction(action);
    }
    
    public Optional<History> getHistoryById(Long id) {
        return historyRepository.findById(id);
    }
    
    public History createHistory(History history) {
        return historyRepository.save(history);
    }
    
    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }
}
