package com.phong.blog.Utils;

import com.phong.blog.Blog.Model.EStatus;
import com.phong.blog.User.Model.User;
import com.phong.blog.User.Model.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthUtils {

    public Boolean isAdmin() {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return true;
        }
        return false;
    }

    public User getUserFromToken() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            return userDetails.getUser();

        } catch (Exception e) {
            return null;
        }

    }


}
