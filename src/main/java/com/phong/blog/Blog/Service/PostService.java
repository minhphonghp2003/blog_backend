package com.phong.blog.Blog.Service;

import com.phong.blog.Blog.DTO.AuthorPostDTO;
import com.phong.blog.Blog.DTO.NewPostDTO;
import com.phong.blog.Blog.Model.*;
import com.phong.blog.Blog.Repository.*;
import com.phong.blog.User.Model.User;
import com.phong.blog.User.Repository.UserRepository;
import com.phong.blog.Utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final ReadingListRepository readingListRepository;
    private final ModelMapper modelMapper;
    private final AuthUtils authUtils;

    public Post findById(int id) {
        return postRepository.findById(id);
    }

    public List<Comment> getComments(int postId) {
        return postRepository.findById(postId).getComments();
    }

    @Transactional
    public Post createPost(NewPostDTO newPostDTO) {
        Post newPost = modelMapper.map(newPostDTO, Post.class);
        User author = authUtils.getUserFromToken();
        ReadingList readingList = readingListRepository.findById(newPostDTO.getReadingListId()).orElse(null);
        Topic topic = topicRepository.findById(newPost.getId()).orElse(null);
        Set<Tag> tags = new HashSet<>();
        for (int i :
                newPostDTO.getTagIds()) {
            tags.add(tagRepository.findById(i));
        }
        newPost.setAuthor(author);
        newPost.setReadingList(readingList);
        newPost.setTopic(topic);
        newPost.setTags(tags);
        postRepository.save(newPost);
        return newPost;
    }

    @Transactional
    public Page<Post> getPostOfAuthor(AuthorPostDTO authorPostDTO) {
        User author = userRepository.findById(authorPostDTO.getAuthorId()).orElse(null);
        Pageable pageable = PageRequest.of(authorPostDTO.getPage(), authorPostDTO.getLimit(), Sort.by("updatedAt").descending());
        return postRepository.findByAuthor(author, pageable);
    }
    public void deletePost(Integer id){
        User user = authUtils.getUserFromToken();
        Post post = postRepository.findById(id).orElse(null);
        if(user.getId().equals( post.getAuthor().getId())){
            postRepository.deleteById(id);
        }else{
            System.out.println(post.getAuthor().getId());
            System.out.println(user.getId());
        }
    }
}
