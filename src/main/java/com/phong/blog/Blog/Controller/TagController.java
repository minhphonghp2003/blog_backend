package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.Model.EStatus;
import com.phong.blog.Blog.Model.Tag;
import com.phong.blog.Blog.Service.TagService;
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

    @Secured("ADMIN")
    @GetMapping("/pending")
    public List<Tag> getPendingTag() {
        return tagService.getPendingTags();
    }

    @Secured({"ADMIN", "AUTHOR"})
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

    @Secured("ADMIN")
    @PutMapping("/")
    public void acceptTagRequest(@RequestBody int id) {
        tagService.updateTagStatus(id, EStatus.ACTIVE);
    }

    @Secured("ADMIN")
    @DeleteMapping("/")
    public void deleteTag(@RequestBody int id) {
        tagService.removeTag(id);
    }
}
