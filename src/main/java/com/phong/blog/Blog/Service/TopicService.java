package com.phong.blog.Blog.Service;

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
        }
        return topicRepository.save(topic);
    }
    public List<Topic> getPendingTopic(){
        return topicRepository.findByStatus(EStatus.PENDING);
    }

    @Transactional
    public void updateTopicStatus(int id, EStatus status){
        Topic topic = topicRepository.findById(id).orElse(null);
        topic.setStatus(status);
        topicRepository.save(topic);
    }

    public void removeTopic(int id) {
        topicRepository.deleteById(id);
    }
}
