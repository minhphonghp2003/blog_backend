package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.DTO.StatusChangeDTO;
import com.phong.blog.Blog.Model.EStatus;
import com.phong.blog.Blog.Model.Tag;
import com.phong.blog.Blog.Service.TagService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tag")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TagController {
    private final TagService tagService;

    @GetMapping("/pending")
    public List<Tag> getPendingTag() {
        return tagService.getPendingTags();
    }

    @PostMapping("/")
    public Tag createTag(@RequestBody String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tagService.createTag(tag);
    }

    @GetMapping("/all")
    public List<Tag> getAllTag() {
        return tagService.getAllTags();
    }

    @PutMapping("/status")
    public void changeStatus(@RequestBody StatusChangeDTO statusChangeDTO) {
        tagService.updateTagStatus(statusChangeDTO);
    }

    @DeleteMapping("/")
    public void deleteTag(@RequestBody int id) {
        tagService.removeTag(id);
    }
}
