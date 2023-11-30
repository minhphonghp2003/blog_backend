package com.phong.blog.User.Controller;

import com.phong.blog.User.DTO.*;
import com.phong.blog.User.Service.Service;
import com.phong.blog.User.Model.User;
import com.phong.blog.Utils.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final Service userService;
    private final ModelMapper modelMapper;
    private final AuthUtils authUtils;

    @Secured({"ADMIN","AUTHOR"})
    @GetMapping("/checkAdmin")
    public Boolean isAdmin(){
        return authUtils.isAdmin();
    }

    @Secured({"ADMIN"})
    @GetMapping("/alluser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/register")
    public LoginDTO register(@RequestBody RegisterDTO registerDTO) {
        User user =  userService.createUser(registerDTO );
        return modelMapper.map(registerDTO,LoginDTO.class);
    }
    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginDTO loginDTO, HttpServletRequest request){
        String token = null;
        try {
            token = userService.getTokenFromCred(loginDTO.getUsername(),loginDTO.getPassword());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        ResponseDTO responseDTO =  new ResponseDTO();
        responseDTO.setToken(token);
        return responseDTO;
    }
    @PostMapping("/recvToken")
    public ResponseEntity<Map<String, String>> getRecvToken(@RequestBody String email){
        try{
            Map<String,String> map =  new HashMap<>();
            map.put("token",userService.updateRecvToken(email));
           return ResponseEntity.status(200).body(map);

        } catch (Exception e){
            return ResponseEntity.status(200).body(null);
        }
    }
    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){
       try{
          userService.resetPassword(updatePasswordDTO.getToken(),updatePasswordDTO.getPassword());
          return ResponseEntity.ok("Success");
       }catch (Exception e){
            return ResponseEntity.status(400).body("Unsuccessful");
       }
    }
    @GetMapping("/userDetail")
    public UserDetailDTO getUserDetail(@RequestHeader (name="Authorization") String token, HttpServletRequest request){
        return userService.getUserDetails(token);
    }

    @Secured({"ADMIN","AUTHOR"})
    @PutMapping("/userDetail")
    public void updateUserDetail(@RequestBody UserDetailUpdateDTO userDetailUpdateDTO){
         userService.updateUserDetail(userDetailUpdateDTO);
    }

    @DeleteMapping("/userSocial")
    public void deleteSocial(@RequestBody String id){
        userService.deleteUserSocial(Integer.valueOf(id));
    }

    @PostMapping("/userSocial")
    public void addUserSocial(@RequestBody SocialUpdateDTO socialUpdateDTO){
        userService.addUserSocial(socialUpdateDTO);
    }
}
