package com.phong.blog.Logging.Service;

import com.phong.blog.Logging.Model.UserActivity;
import com.phong.blog.Logging.Repository.UserActivityRepository;
import com.phong.blog.User.Model.User;
import com.phong.blog.User.Repository.UserRepository;
import com.phong.blog.Utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserActivityService {
    private final UserActivityRepository userActivityRepository;
    private final AuthUtils authUtils;
    private final UserRepository userRepository;
    public void createUserActivity(UserActivity userActivity) {
        User user = authUtils.getUserFromToken();
        userActivity.setUser(user);
        userActivityRepository.save(userActivity);
    }
    public List<UserActivity> getUserActivity(String uuid){
        User user  = null;
       if(authUtils.isAdmin() && uuid != null) {
          user = userRepository.findById(UUID.fromString(uuid)).orElse(null);
       }else{
           user = authUtils.getUserFromToken();
       }
       if(user == null){
           return null;
       }
        return userActivityRepository.findAllByUser(user);
    }
}
