package com.phong.blog.Authentication.Repository;

import com.phong.blog.Authentication.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<UserCredential, Integer> {
}
