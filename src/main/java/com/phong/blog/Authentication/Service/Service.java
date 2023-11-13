package com.phong.blog.Authentication.Service;

import com.phong.blog.Authentication.DTO.RegisterDTO;
import com.phong.blog.Authentication.Repository.CredentialRepository;
import com.phong.blog.Authentication.Repository.UserRepository;
import com.phong.blog.Authentication.model.User;
import com.phong.blog.Authentication.model.UserCredential;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Transactional
public class Service {
    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User createUser(RegisterDTO registerDTO, User user, UserCredential credential) {
        String password = registerDTO.getPassword();
        String hashed = passwordEncoder.encode(password);
        User newUser = userRepository.save(user);
        credential.setUser(newUser);
        credential.setHashedPassword(hashed);
        credentialRepository.save(credential);
        return newUser;

    }
}
