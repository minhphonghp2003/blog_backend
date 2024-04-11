package com.phong.blog.config;

import com.phong.blog.User.Model.Role;
import com.phong.blog.User.Model.UserDetails;
import com.phong.blog.User.Service.RoleService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends GenericFilter {
    private final RoleService roleService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request = (HttpServletRequest) request;
        String apiMethod = ((HttpServletRequest) request).getMethod();
        String apiURI = ((HttpServletRequest) request).getRequestURI();
        List<Role> rolesHaveAction = roleService.getRoleHaveAction(apiURI);
        if (rolesHaveAction.size() != 0 && !apiMethod.equals("OPTIONS")) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                ((HttpServletResponse) response).setStatus(401);
                response.getOutputStream().write("Forbidden".getBytes());
                return;
            }
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<Role> userRoles = userDetails.getUser().getRoles().stream().toList();

            boolean isValid = false;
            for (int i = 0; i < userRoles.size(); i++) {
                if (rolesHaveAction.contains(userRoles.get(i))) {
                    isValid = true;
                }
            }
            if (!isValid) {
                ((HttpServletResponse) response).setStatus(403);
                response.getOutputStream().write("Forbidden".getBytes());
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
