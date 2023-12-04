package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.Post;
import com.phong.blog.User.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    public Post findById(int id);
    public Page<Post> findByAuthor(User author, Pageable pageable);
    public void deleteById(Integer id);
}
