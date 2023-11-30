package com.phong.blog.Utils;

import com.phong.blog.Blog.Model.EStatus;
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


}
