package com.phong.blog.User.Controller;

import com.phong.blog.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("status")
@RequiredArgsConstructor
public class StatusController {
    private final UserService userService;
}
