package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.DTO.ReaderDTO;
import com.phong.blog.Blog.Model.Reader;
import com.phong.blog.Blog.Service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("reader")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReaderController {
    private final ModelMapper modelMapper;
    private final ReaderService readerService;

    @PostMapping("/")
    public Reader createReader(@RequestBody ReaderDTO readerDTO){
        Reader reader = modelMapper.map(readerDTO,Reader.class);
        return readerService.createReader(reader);
    }

    @GetMapping("/")
    public Reader getReader(String id){
        return readerService.getReader(UUID.fromString(id));
    }
}
