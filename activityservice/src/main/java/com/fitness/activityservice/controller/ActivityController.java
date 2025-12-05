package com.fitness.activityservice.controller;


import com.fitness.activityservice.activity.ActivityService;
import com.fitness.activityservice.DTO.ActivityRequest;
import com.fitness.activityservice.DTO.ActivityResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
      private ActivityService activityService;

      public  ActivityController(ActivityService activityService){
          this.activityService=activityService;
      }

      @PostMapping()
    public ResponseEntity<ActivityResponse> tarckActivity(@RequestBody ActivityRequest request){
          return ResponseEntity.ok(activityService.trackActivity(request));
      }

      @GetMapping("/")
    public ResponseEntity<List<ActivityResponse>> getAllActivity(){
          return ResponseEntity.ok(activityService.getAllActivities());
      }


}
