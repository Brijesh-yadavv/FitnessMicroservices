package com.fitness.activityservice.DTO;
import com.fitness.activityservice.entity.ActivityType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;



@Data
public class ActivityResponse {
    private String id;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String,Object> additionalMaterics;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;









}
