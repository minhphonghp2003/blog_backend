package com.phong.blog.Blog.Service;

import com.phong.blog.Blog.DTO.ReadingListDTO;
import com.phong.blog.Blog.DTO.StatusChangeDTO;
import com.phong.blog.Blog.Model.EStatus;
import com.phong.blog.Blog.Model.ReadingList;
import com.phong.blog.Blog.Repository.ReadingListRepository;
import com.phong.blog.Utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingListService {
    private final ReadingListRepository readingListRepository;
    private final AuthUtils authUtils;
    public ReadingList createReadingList( ReadingList readingList){
        if(authUtils.isAdmin()){
            readingList.setStatus(EStatus.ACTIVE);
        }
        return readingListRepository.save(readingList);
    }
    public List<ReadingList> getAllReadingList(){
        return readingListRepository.findAll();
    }

    public void updateRListStatus(StatusChangeDTO statusChangeDTO) {
        ReadingList rlist = readingListRepository.findById(statusChangeDTO.getTargetId()).orElse(null);
        EStatus status = statusChangeDTO.getStatus();
        rlist.setStatus(status);
        readingListRepository.save(rlist);

    }
}
