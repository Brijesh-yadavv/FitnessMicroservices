package com.fitness.activityservice.services;

import com.fitness.activityservice.DTO.ActivityRequest;
import com.fitness.activityservice.DTO.ActivityResponse;
import com.fitness.activityservice.entity.Activity;
import com.fitness.activityservice.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidateService userValidateService;


    public ActivityService(ActivityRepository activityRepository, UserValidateService userValidateService){
        this.activityRepository=activityRepository;
        this.userValidateService = userValidateService;
    }
    public  ActivityResponse trackActivity(ActivityRequest request) {

        boolean isValidUSer=userValidateService.validateUser(request.getUserId());

        if(!isValidUSer){
            throw new RuntimeException("Invalid User: "+request.getUserId());

        }

        Activity activity = Activity.builder().userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMaterics(request.getAdditionalMaterics())
                .build();

        Activity savedActivity=activityRepository.save(activity);

        ActivityResponse activityResponse=new ActivityResponse();

        return mapToResponse(savedActivity);

    }

    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response=new ActivityResponse();

        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setCreatedAt(activity.getCreatedAt());
        response.setStartTime(activity.getStartTime());
        response.setUpdatedAt(activity.getUpdatedAt());
        response.setAdditionalMaterics(activity.getAdditionalMaterics());

        return response;

    }

    public  List<ActivityResponse> getAllActivities() {
        List<Activity> activityList=activityRepository.findAll();
        List<ActivityResponse> responseList=new ArrayList<>();
        for(Activity activity:activityList){
            ActivityResponse activityResponse=new ActivityResponse();
            activityResponse.setId(activity.getId());
            activityResponse.setUserId(activity.getUserId());
            activityResponse.setDuration(activity.getDuration());
            activityResponse.setStartTime(activity.getStartTime());
            activityResponse.setUpdatedAt(activity.getUpdatedAt());
            activityResponse.setCreatedAt(activity.getCreatedAt());
            activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
            activityResponse.setType(activity.getType());
            activityResponse.setAdditionalMaterics(activity.getAdditionalMaterics());
            responseList.add(activityResponse);
        }
        return responseList;
    }
}
