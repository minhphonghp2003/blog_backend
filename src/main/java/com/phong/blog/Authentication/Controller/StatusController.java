package com.phong.blog.Authentication.Controller;

import com.phong.blog.Authentication.Service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("status")
@RequiredArgsConstructor
public class StatusController {
    private final Service userService;
}
