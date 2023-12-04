package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.DTO.ReadingListDTO;
import com.phong.blog.Blog.Model.EStatus;
import com.phong.blog.Blog.Model.ReadingList;
import com.phong.blog.Blog.Repository.ReadingListRepository;
import com.phong.blog.Blog.Service.ReadingListService;
import com.phong.blog.Utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("readingList")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReadingListController {
    private final ReadingListService readingListService;
    private final ModelMapper modelMapper;
    private final AuthUtils authUtils;

    @Secured({"ADMIN","AUTHOR"})
    @PostMapping("/")
    public ReadingList createReadingList(@RequestBody ReadingListDTO readingListDTO) {
        ReadingList readingList = modelMapper.map(readingListDTO, ReadingList.class);
        return readingListService.createReadingList(readingList);
    }

    @GetMapping("/all")
    public List<ReadingListDTO> getAllReadingList(){
        List<ReadingList> readingLists = readingListService.getAllReadingList();
        List<ReadingListDTO> readingListDTOS = modelMapper.map(readingLists, new TypeToken<List<ReadingListDTO>>() {}.getType());

        return readingListDTOS;
    }

}
