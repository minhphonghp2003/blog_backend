package com.phong.blog.User.Controller;

import com.phong.blog.User.DTO.LoginDTO;
import com.phong.blog.User.DTO.RegisterDTO;
import com.phong.blog.User.DTO.ResponseDTO;
import com.phong.blog.User.Service.Service;
import com.phong.blog.User.model.User;
import com.phong.blog.User.model.UserCredential;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class UserController {
    private final Service userService;
    private final ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Secured({"ADMIN","AUTHOR"})
    @GetMapping("/alluser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/register")
    public LoginDTO register(@RequestBody RegisterDTO registerDTO) {
        User user =  userService.createUser(registerDTO );
        return modelMapper.map(registerDTO,LoginDTO.class);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginDTO loginDTO, HttpServletRequest request){
        logger.info("Request from "+request.getRemoteAddr());
        String token = userService.getTokenFromCred(loginDTO.getUsername(),loginDTO.getPassword());
        ResponseDTO responseDTO =  new ResponseDTO();
        responseDTO.setToken(token);
        return responseDTO;

    }
}
