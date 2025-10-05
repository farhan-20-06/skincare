package com.scinkare.config;

import com.scinkare.model.*;
import com.scinkare.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(UserProfileRepository userRepo,
                               GoalRepository goalRepo,
                               MorningRoutineRepository morningRepo,
                               NightRoutineRepository nightRepo,
                               ProgressTrackingRepository progressRepo,
                               HistoryRepository historyRepo) {
        return args -> {
            if (userRepo.count() > 0) {
                return; // already seeded
            }

            UserProfile user = new UserProfile();
            user.setUsername("demo");
            user.setSkinType("normal");
            user = userRepo.save(user);

            Goal g = new Goal();
            g.setGoalTitle("Hydration");
            g.setGoalType("hydration");
            g.setDateEntry(LocalDate.now());
            g.setGoalDetail("Drink more water and use moisturizer");
            g.setUserProfile(user);
            goalRepo.save(g);

            MorningRoutine mr = new MorningRoutine();
            mr.setUserProfile(user);
            mr.setProductName("Gentle Cleanser");
            mr.setBrand("BrandA");
            mr.setCategory("cleanser");
            morningRepo.save(mr);

            NightRoutine nr = new NightRoutine();
            nr.setUserProfile(user);
            nr.setProductName("Retinol Serum");
            nr.setBrand("BrandB");
            nr.setCategory("serum");
            nightRepo.save(nr);

            ProgressTracking pt = new ProgressTracking();
            pt.setUserProfile(user);
            pt.setAcneScore(3);
            pt.setGlowScore(7);
            pt.setNotes("Skin improving");
            progressRepo.save(pt);

            History h = new History();
            h.setUserProfile(user);
            h.setTableName("user_profiles");
            h.setAction("CREATE");
            h.setRecordId(user.getId());
            historyRepo.save(h);
        };
    }
}


