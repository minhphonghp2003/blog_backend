package com.phong.blog.User.Controller;

import com.phong.blog.User.DTO.LoginDTO;
import com.phong.blog.User.DTO.RegisterDTO;
import com.phong.blog.User.DTO.ResponseDTO;
import com.phong.blog.User.DTO.UpdatePasswordDTO;
import com.phong.blog.User.Service.Service;
import com.phong.blog.User.model.User;
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
@RequestMapping("auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final Service userService;
    private final ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(UserController.class);


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
}
