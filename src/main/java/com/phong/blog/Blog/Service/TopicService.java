package com.phong.blog.Blog.Service;

import com.phong.blog.Blog.DTO.StatusChangeDTO;
import com.phong.blog.Blog.DTO.TopicDTO;
import com.phong.blog.Blog.Model.EStatus;
import com.phong.blog.Blog.Model.Topic;
import com.phong.blog.Blog.Repository.TopicRepository;
import com.phong.blog.Utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final ModelMapper modelMapper;
    private final AuthUtils authUtils;

    public Topic createTopic(Topic topic) {

        if(authUtils.isAdmin()){
            topic.setStatus(EStatus.ACTIVE);
        }else{

            topic.setStatus(EStatus.PENDING);
        }
        return topicRepository.save(topic);
    }
    public List<Topic> getPendingTopic(){
        return topicRepository.findByStatus(EStatus.PENDING);
    }

    @Transactional
    public void updateTopicStatus(StatusChangeDTO statusChangeDTO){
        Topic topic = topicRepository.findById(statusChangeDTO.getTargetId()).orElse(null);
        topic.setStatus(statusChangeDTO.getStatus());
        topicRepository.save(topic);
    }

    public void removeTopic(int id) {
        topicRepository.deleteById(id);
    }

    public List<Topic> getAllTopic() {
        if(authUtils.getUserFromToken() == null){
            return topicRepository.findByStatus(EStatus.ACTIVE);
        }
        return topicRepository.findAll();
    }
}
