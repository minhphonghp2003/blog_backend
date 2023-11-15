package com.phong.blog.User.Repository;

import com.phong.blog.User.model.User;
import com.phong.blog.User.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<UserCredential, Integer> {
    UserCredential findByUsername(String username);
}
