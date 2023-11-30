package com.phong.blog.Blog.Service;

import com.phong.blog.Blog.DTO.ReadingListDTO;
import com.phong.blog.Blog.Model.ReadingList;
import com.phong.blog.Blog.Repository.ReadingListRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class ReadingListService {
    private final ReadingListRepository readingListRepository;

    public ReadingList createReadingList( ReadingList readingList){
        return readingListRepository.save(readingList);
    }

}
