package com.phong.blog.Blog.Service;

import com.phong.blog.Blog.DTO.*;
import com.phong.blog.Blog.Model.*;
import com.phong.blog.Blog.Repository.*;
import com.phong.blog.User.Model.User;
import com.phong.blog.User.Repository.UserRepository;
import com.phong.blog.Utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final ReadingListRepository readingListRepository;
    private final ModelMapper modelMapper;
    private final AuthUtils authUtils;

    public Post findById(int id) {
        return postRepository.findById(id);
    }



    @Transactional
    public Post createPost(NewPostDTO newPostDTO) {
        TypeMap<NewPostDTO, Post> propertyMapper = modelMapper.getTypeMap(NewPostDTO.class, Post.class);
        if (propertyMapper == null) { // if not  already added
            propertyMapper = modelMapper.createTypeMap(NewPostDTO.class, Post.class);
            propertyMapper.addMappings(mapper -> mapper.skip(Post::setId));
        }

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
        Post returnPost = postRepository.save(newPost);
        return newPost;
    }

    @Transactional
    public Page<Post> getPostOfAuthor(AuthorPostDTO authorPostDTO) {
        User author = userRepository.findById(authorPostDTO.getAuthorId()).orElse(null);
        Pageable pageable = PageRequest.of(authorPostDTO.getPage(), authorPostDTO.getLimit(), Sort.by("updatedAt").descending());
        return postRepository.findByAuthor(author, pageable);
    }

    public Page<Post> getAllPost(AllPostReqDTO allPostReqDTO) {
        Page<Post> posts = null;
        Pageable pageable = PageRequest.of(allPostReqDTO.getPage(), allPostReqDTO.getLimit(), Sort.by(String.valueOf(allPostReqDTO.getSortBy())).descending());
        if (String.valueOf(allPostReqDTO.getGetBy()).equals("topic")) {
            posts = postRepository.findByTopic(topicRepository.findById(allPostReqDTO.getId()).orElse(null), pageable);
        } else if (String.valueOf(allPostReqDTO.getGetBy()).equals("readinglist")) {
            posts = postRepository.findByReadingList(readingListRepository.findById(allPostReqDTO.getId()).orElse(null), pageable);
        } else if (String.valueOf(allPostReqDTO.getGetBy()).equals("author")) {
            posts = postRepository.findByAuthor(userRepository.findById(allPostReqDTO.getUuId()).orElse(null), pageable);
        } else {
            posts = postRepository.findAll(pageable);
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

    public PostDTO getPost(int id) {
        List<Post> nextPosts = postRepository.findByIdGreaterThan(id).orElse(null);
        Post post = postRepository.findById(id);
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        if (nextPosts.size() > 0) {
            Post nextPost = nextPosts.get(0);
            postDTO.setNextId(nextPost.getId());
            postDTO.setNextTitle(nextPost.getTitle());
            postDTO.setNextImageLink(nextPost.getImageLink());
        }
        return postDTO;
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
