package com.phong.blog.Authentication.Controller;

import com.phong.blog.Authentication.DTO.LoginDTO;
import com.phong.blog.Authentication.DTO.RegisterDTO;
import com.phong.blog.Authentication.Service.Service;
import com.phong.blog.Authentication.model.User;
import com.phong.blog.Authentication.model.UserCredential;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class UserController {
    private final Service userService;
    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/user")
    public LoginDTO register(@RequestBody RegisterDTO registerDTO) {
        User newUser = modelMapper.map(registerDTO, User.class);
        UserCredential credential = modelMapper.map(registerDTO, UserCredential.class);
        User user =  userService.createUser(registerDTO, newUser, credential);
        LoginDTO loginDto =  modelMapper.map(user, LoginDTO.class);
        loginDto.setEmail(registerDTO.getEmail());
        return loginDto;
    }
}
