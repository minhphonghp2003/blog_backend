package com.phong.blog.User.Service;

import com.phong.blog.Blog.DTO.PostDTO;
import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Service.PostService;
import com.phong.blog.User.DTO.*;
import com.phong.blog.User.Repository.*;
import com.phong.blog.User.Model.*;
import com.phong.blog.Utils.AuthUtils;
import com.phong.blog.Utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final AuthUtils authUtils;
    private final SocialRepository socialRepository;
    private final CredentialRepository credentialRepository;
    private final RoleRepository roleRepository;
    private final PostService postService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    @Transactional
    public User createUser(RegisterDTO registerDTO) {
        Status status = statusRepository.findByName(EStatus.valueOf(registerDTO.getStatus()));
        User user = modelMapper.map(registerDTO, User.class);
        user.setStatus(status);
        user.setAvatar("avatar/" + registerDTO.getUsername());
        user.setRoles(new HashSet<>());
        registerDTO.getRoles().stream().forEach(r -> {
            Role role = roleRepository.findByName(ERole.valueOf(r));
            user.getRoles().add(role);
        });
        UserCredential credential = modelMapper.map(registerDTO, UserCredential.class);
        User newUser = userRepository.save(user);

        String hashed = passwordEncoder.encode(registerDTO.getPassword());
        credential.setUser(newUser);
        credential.setHashedPassword(hashed);
        credentialRepository.save(credential);
        return newUser;
    }

    private UserDetails getUserDetailWhenValidated(String username, String password) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        return userDetails;
    }

    public String getTokenFromCred(String username, String password) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        UserDetails userDetails = getUserDetailWhenValidated(username, password);
        String jwt = jwtUtils.generateToken(userDetails.getUser());
        return jwt;
    }

    @Transactional
    public String updateRecvToken(String email) {
        String token = UUID.randomUUID().toString();
        credentialRepository.updateRecvToken(email, token);
        TaskExecutor theExecutor = new SimpleAsyncTaskExecutor();
        theExecutor.execute(() -> {
            try {
                Thread.sleep(5 * 1000 * 60);
                credentialRepository.removeRecvToken(email);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

        });
        return token;
    }

    @Transactional
    public void resetPassword(String token, String password) {
        String hashed = passwordEncoder.encode(password);
        credentialRepository.updatePassword(token, hashed);
    }

    //    TODO: fetch user works statistic
    public UserDetailDTO getUserDetails() {
        User user = authUtils.getUserFromToken();
        if (user == null) {
            return null;
        }

        UserDetailDTO userDetailDTO = modelMapper.map(user, UserDetailDTO.class);
        modelMapper.map(user, userDetailDTO.getUserInformation());

        UserCredential credential = user.getCredential();
        userDetailDTO.setUsername(credential.getUsername());
        userDetailDTO.setEmail(credential.getEmail());

        return userDetailDTO;
    }

    public void updateUserDetail(UserDetailUpdateDTO userDetailUpdateDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserDetails) (authentication.getPrincipal())).getUser();
        modelMapper.map(userDetailUpdateDTO, user);
        System.out.println(user.getId());
        UserCredential userCredential =credentialRepository.findByUserId(user.getId());
        userCredential.setEmail(userDetailUpdateDTO.getEmail());
        credentialRepository.save(userCredential);
        userRepository.save(user);
    }

    public void addUserSocial(SocialUpdateDTO socialUpdateDTO) {
        User user = userRepository.findById(socialUpdateDTO.getUserId()).orElse(null);
        if (user == null) {
            return;
        }
        Social social = new Social();
        social.setUser(user);
        social.setLink(socialUpdateDTO.getLink());
        social.setName(socialUpdateDTO.getName());
        socialRepository.save(social);
    }

    public void deleteUserSocial(Integer id) {
        socialRepository.deleteById(id);
    }

    public User getAuthorDetail(UUID id) {
        return userRepository.findById(id).orElse(null);
    }
}
