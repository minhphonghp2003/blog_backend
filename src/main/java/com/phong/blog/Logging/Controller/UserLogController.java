package com.phong.blog.Logging.Controller;


import com.phong.blog.Logging.DTO.LogDTO;
import com.phong.blog.Logging.Model.UserLog;
import com.phong.blog.Logging.Service.UserLogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("log")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserLogController {
    private final UserLogService userLogService;
    private final ModelMapper modelMapper;

    @PostMapping("/")
    public void createUserLog(@RequestBody UserLog userLogDTO){
        userLogService.createUserLog(userLogDTO);
    }

    @GetMapping("/")
    public List<LogDTO> getUserLog(){
        List<UserLog> userLogs = userLogService.getUserLog();
        return modelMapper.map(userLogs, new TypeToken<List<LogDTO>>() {}.getType());

    }
}
