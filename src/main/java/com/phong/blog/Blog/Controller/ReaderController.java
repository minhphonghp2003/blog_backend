package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.DTO.ReaderDTO;
import com.phong.blog.Blog.Model.Reader;
import com.phong.blog.Blog.Service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("reader")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReaderController {
    private final ModelMapper modelMapper;
    private final ReaderService readerService;

    @PostMapping("/")
    public Reader createReader(@RequestBody ReaderDTO readerDTO) {
        Reader reader = modelMapper.map(readerDTO, Reader.class);
        return readerService.createReader(reader);
    }

    @GetMapping("/all")
    public Page<Reader> getAllReader(Integer page) {
        return readerService.getAllReader(page);
    }
    @GetMapping("/detail")
    public Reader getReaderDetail(String id){
        return readerService.getReader(UUID.fromString(id));
    }
}
