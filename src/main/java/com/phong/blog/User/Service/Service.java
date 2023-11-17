package com.phong.blog.User.Service;

import com.phong.blog.User.DTO.RegisterDTO;
import com.phong.blog.User.Repository.CredentialRepository;
import com.phong.blog.User.Repository.RoleRepository;
import com.phong.blog.User.Repository.StatusRepository;
import com.phong.blog.User.Repository.UserRepository;
import com.phong.blog.User.model.*;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final CredentialRepository credentialRepository;
    private final RoleRepository roleRepository;
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
                Thread.sleep(5 * 1000 * 60 );
                credentialRepository.removeRecvToken(email);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

        });
        return token;
    }
    @Transactional
    public void resetPassword(String token, String password){
        String hashed = passwordEncoder.encode(password);
        credentialRepository.updatePassword(token,hashed);
    }

}
