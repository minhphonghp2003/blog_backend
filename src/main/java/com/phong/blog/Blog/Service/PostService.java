package com.phong.blog.Blog.Service;

import com.phong.blog.Blog.DTO.*;
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
        Topic topic = topicRepository.findById(newPostDTO.getTopicId()).orElse(null);
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

    public Page<Post> getAllPost(AllPostReqDTO allPostReqDTO) {

        Pageable pageable = PageRequest.of(allPostReqDTO.getPage(), allPostReqDTO.getLimit(), Sort.by(String.valueOf(allPostReqDTO.getSortBy())).descending());
        return postRepository.findAll(pageable);
    }

    public Page<Post> getAllPostBy(AllPostByReq allPostByReq) {
        Page<Post> posts = null;
        Pageable pageable = PageRequest.of(allPostByReq.getPage(), allPostByReq.getLimit(), Sort.by(String.valueOf(allPostByReq.getSortBy())).descending());
        if (String.valueOf(allPostByReq.getGetBy()).equals("topic")) {
            posts = postRepository.findByTopic(topicRepository.findById(allPostByReq.getId()).orElse(null), pageable);
        }
        if (String.valueOf(allPostByReq.getGetBy()).equals("readinglist")) {
            posts = postRepository.findByReadingList(readingListRepository.findById(allPostByReq.getId()).orElse(null), pageable);
        }
        return posts;
    }

    public void deletePost(Integer id) {
        User user = authUtils.getUserFromToken();
        Post post = postRepository.findById(id).orElse(null);
        if (user.getId().equals(post.getAuthor().getId())) {
            postRepository.deleteById(id);
        } else {
            System.out.println(post.getAuthor().getId());
            System.out.println(user.getId());
        }
    }

    public Post getPost(int id) {
        return postRepository.findById(id);
    }

    public Post updatePost(UpdatePostDTO updatePostDTO) {
        Post post = postRepository.findById(updatePostDTO.getId());
        post.setTitle(updatePostDTO.getTitle());
        post.setForeword(updatePostDTO.getForeword());
        ReadingList readingList = readingListRepository.findById(updatePostDTO.getReadingListId()).orElse(null);
        Topic topic = topicRepository.findById(updatePostDTO.getTopicId()).orElse(null);
        Set<Tag> tags = new HashSet<>();
        for (int i :
                updatePostDTO.getTagIds()) {
            tags.add(tagRepository.findById(i));
        }
        post.setReadingList(readingList);
        post.setTopic(topic);
        post.setTags(tags);
        User author = authUtils.getUserFromToken();
        if (author.getId().equals(post.getAuthor().getId())) {
            postRepository.save(post);
        }
        return post;
    }
}
