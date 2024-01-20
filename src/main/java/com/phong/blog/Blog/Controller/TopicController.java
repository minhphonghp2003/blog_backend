package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.DTO.ReadingListDTO;
import com.phong.blog.Blog.DTO.TopicDTO;
import com.phong.blog.Blog.Model.EStatus;
import com.phong.blog.Blog.Model.ReadingList;
import com.phong.blog.Blog.Model.Topic;
import com.phong.blog.Blog.Service.TopicService;
import com.phong.blog.Logging.Model.UserActivity;
import com.phong.blog.Logging.Service.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("topic")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TopicController {
    private final TopicService topicService;
    private final UserActivityService userActivityService;
    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<TopicDTO> getAllTopic() {
        {
            List<Topic> topics = topicService.getAllTopic();
            List<TopicDTO> topicDTOS = modelMapper.map(topics, new TypeToken<List<TopicDTO>>() {
            }.getType());

            return topicDTOS;
        }
    }

    @Secured({"ADMIN", "AUTHOR"})
    @PostMapping("/")
    public Topic createTopic(@RequestBody TopicDTO topicDTO) {
        userActivityService.createUserActivity(new UserActivity("Create topic " + topicDTO.getName()));
        return topicService.createTopic(modelMapper.map(topicDTO, Topic.class));
    }

    @Secured("ADMIN")
    @GetMapping("/pending")
    public List<TopicDTO> getPendingTopic() {
        List<Topic> topics = topicService.getPendingTopic();
        return modelMapper.map(topics, new TypeToken<List<TopicDTO>>() {
        }.getType());
    }

    @Secured("ADMIN")
    @PutMapping("/")
    public void acceptPendingTopic(@RequestBody int id) {
        topicService.updateTopicStatus(id, EStatus.ACTIVE);
    }

    @Secured("ADMIN")
    @DeleteMapping("/")
    public void deleteTopic(@RequestBody int id) {
        topicService.removeTopic(id);
        userActivityService.createUserActivity(new UserActivity("Delete topic " + id));
    }

}
