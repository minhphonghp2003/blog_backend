package com.phong.blog.User.Controller;

import com.phong.blog.Logging.Model.UserActivity;
import com.phong.blog.Logging.Service.UserActivityService;
import com.phong.blog.User.DTO.*;
import com.phong.blog.User.Service.UserService;
import com.phong.blog.User.Model.User;
import com.phong.blog.Utils.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;
    private final UserActivityService userActivityService;
    private final ModelMapper modelMapper;
    private final AuthUtils authUtils;
    @Value("${frontEnd}")
    private String frontEnd;

    @GetMapping("/checkAdmin")
    public Boolean isAdmin() {
        return authUtils.isAdmin();
    }

    @GetMapping("/alluser")
    public List<AllUserDTO> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return modelMapper.map(allUser, new TypeToken<List<AllUserDTO>>() {
        }.getType());

    }

    @PostMapping("/register")
    public LoginDTO register(@RequestBody RegisterDTO registerDTO) {
        User user = userService.createUser(registerDTO);
        return modelMapper.map(registerDTO, LoginDTO.class);
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        String token = null;
        try {
            token = userService.getTokenFromCred(loginDTO.getUsername(), loginDTO.getPassword());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setToken(token);
        return responseDTO;
    }

    @PostMapping("/recvToken")
    public ResponseEntity<Map<String, String>> getRecvToken(@RequestBody String email) {
        try {
            String token = userService.updateRecvToken(email);
            ResetEmailDTO resetEmailDTO = new ResetEmailDTO(email, token, frontEnd + "/forgot");
            userService.sendEmail(resetEmailDTO);
            Map<String, String> map = new HashMap<>();
            map.put("status", "OK");
            return ResponseEntity.status(200).body(map);

        } catch (Exception e) {
            return ResponseEntity.status(200).body(null);
        }
    }

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        try {
            userService.resetPassword(updatePasswordDTO.getToken(), updatePasswordDTO.getPassword());

            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Unsuccessful");
        }
    }

    @GetMapping("/userDetail")
    public UserDetailDTO getUserDetail() {
        return userService.getUserDetails();
    }

    @PutMapping("/userDetail")
    public void updateUserDetail(@RequestBody UserDetailUpdateDTO userDetailUpdateDTO) {
        userService.updateUserDetail(userDetailUpdateDTO);
        userActivityService.createUserActivity(new UserActivity("Change user details"));
    }

    @DeleteMapping("/userSocial")
    public void deleteSocial(@RequestBody String id) {
        userService.deleteUserSocial(Integer.valueOf(id));
    }

    @PostMapping("/userSocial")
    public void addUserSocial(@RequestBody SocialUpdateDTO socialUpdateDTO) {
        userService.addUserSocial(socialUpdateDTO);
    }

    @GetMapping("/author")
    public AuthorDTO getAuthor(String id) {
        return modelMapper.map(userService.getAuthorDetail(UUID.fromString(id)), AuthorDTO.class);
    }

}
