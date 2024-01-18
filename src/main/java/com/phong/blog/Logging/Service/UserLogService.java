package com.phong.blog.Logging.Service;

import com.phong.blog.Logging.Model.UserLog;
import com.phong.blog.Logging.Repository.UserLogRepository;
import com.phong.blog.User.Model.User;
import com.phong.blog.User.Repository.UserRepository;
import com.phong.blog.Utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserLogService {
    private final UserLogRepository userLogRepository;
    private final UserRepository userRepository;
    private final AuthUtils authUtils;
    public void createUserLog(UserLog userLog) {
//        User user = userRepository.findById(userLogDTO.getUserId()).orElse(null);
        User user = authUtils.getUserFromToken();
        if (user == null) {
            return;
        }
        userLog.setUser(user);
        userLogRepository.save(userLog);
    }

    @Secured({"AUTHOR","ADMIN"})
    public List<UserLog> getUserLog() {
        User user = authUtils.getUserFromToken();
        return userLogRepository.getUserLog(user.getId());
    }
}
