package com.phong.blog.Blog.Service;

import com.phong.blog.Blog.DTO.StatusChangeDTO;
import com.phong.blog.Blog.Model.EStatus;
import com.phong.blog.Blog.Model.Tag;
import com.phong.blog.Blog.Repository.TagRepository;
import com.phong.blog.Utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final AuthUtils authUtils;

    public Tag createTag(Tag tag){
        if(authUtils.isAdmin()){
            tag.setStatus(EStatus.ACTIVE);
        }
        return tagRepository.save(tag);
    }

    public void updateTagStatus(StatusChangeDTO statusChangeDTO){
       Tag tag = tagRepository.findById(statusChangeDTO.getTargetId()) ;
       tag.setStatus(statusChangeDTO.getStatus());
       tagRepository.save(tag);
    }

    public void removeTag(int id){
        tagRepository.deleteById(id);
    }

    public List<Tag> getPendingTags() {
        return tagRepository.findAllByStatus(EStatus.PENDING);
    }
    public List<Tag> getAllTags(){
       return tagRepository.findAll();
    }
}
