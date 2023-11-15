package com.phong.blog.User.Repository;

import com.phong.blog.User.model.User;
import com.phong.blog.User.model.UserCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final CredentialRepository credentialRepository;
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         UserCredential userCredential=  credentialRepository.findByUsername(username);
         User user = userRepository.findById(userCredential.getUser().getId()).orElse(new User());
         return new com.phong.blog.User.model.UserDetails(user);
    }
}
