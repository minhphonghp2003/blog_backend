package com.phong.blog.Searching.Controller;

import com.phong.blog.Searching.Model.Demo;
import com.phong.blog.Searching.Service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("search")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {
    private final Service service;


    @PostMapping("/demo")
    public Demo createDemo(@RequestBody Demo demo){
        return service.createDemo(demo);
    }
    @GetMapping("/demo/all")
    public Page<Demo> getAllDemo(){
        return service.getAllDemo();
    }
}
