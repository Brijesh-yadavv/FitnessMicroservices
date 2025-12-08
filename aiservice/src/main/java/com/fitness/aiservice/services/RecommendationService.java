package com.fitness.aiservice.services;

import lombok.RequiredArgsConstructor;
import com.fitness.aiservice.model.Recommendation;
import org.springframework.stereotype.Service;
import com.fitness.aiservice.repository.RecommendationRepositary;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepositary recommendationRepositary;



    public  List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepositary.findByUserId(userId);
    }

    public  Recommendation getActivityRecommendation(String activityId) {
        return recommendationRepositary.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("No Recommendation found for this activity"+activityId));
    }
}
