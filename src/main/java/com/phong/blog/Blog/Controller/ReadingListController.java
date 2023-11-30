package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.DTO.ReadingListDTO;
import com.phong.blog.Blog.Model.ReadingList;
import com.phong.blog.Blog.Repository.ReadingListRepository;
import com.phong.blog.Blog.Service.ReadingListService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("readingList")
@RequiredArgsConstructor
public class ReadingListController {
    private final ReadingListService readingListService;
    private final ModelMapper modelMapper;
    @Secured("ADMIN")
    @PostMapping("/")
    public ReadingList createReadingList(@RequestBody ReadingListDTO readingListDTO){
        return readingListService.createReadingList(modelMapper.map(readingListDTO,ReadingList.class));
    }

}
