package com.phong.blog.Logging.Controller;


import com.phong.blog.Logging.DTO.LogDTO;
import com.phong.blog.Logging.DTO.UserActivityDTO;
import com.phong.blog.Logging.Model.UserActivity;
import com.phong.blog.Logging.Model.UserLog;
import com.phong.blog.Logging.Repository.UserActivityRepository;
import com.phong.blog.Logging.Service.UserActivityService;
import com.phong.blog.Logging.Service.UserLogService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("log")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Secured({"AUTHOR","ADMIN"})
public class UserLogController {
    private final UserLogService userLogService;
    private final ModelMapper modelMapper;
    private final UserActivityService userActivityService;

    @PostMapping("/")
    public void createUserLog(@RequestBody UserLog userLogDTO){
        userLogService.createUserLog(userLogDTO);
    }


    @GetMapping("/")
    public List<LogDTO> getUserLog(){
        List<UserLog> userLogs = userLogService.getUserLog();
        return modelMapper.map(userLogs, new TypeToken<List<LogDTO>>() {}.getType());

    }
    @PostMapping("/activity")
    public void createUserActivity(@RequestBody UserActivity userActivity){
        userActivityService.createUserActivity(userActivity);
    }
    @GetMapping("/activity")
    public List<UserActivityDTO> getUserActivity(@Nullable String userId){
        List<UserActivity> userActivities = userActivityService.getUserActivity(userId);
        return modelMapper.map(userActivities, new TypeToken<List<UserActivityDTO>>() {}.getType());
    }
}
