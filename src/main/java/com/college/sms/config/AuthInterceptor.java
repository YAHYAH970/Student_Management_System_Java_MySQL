package com.college.sms.config;

import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if (path.startsWith("/api/") && !path.equals("/api/auth/login") && !path.equals("/api/auth/logout")) {
            if (request.getSession(false) == null || request.getSession(false).getAttribute("adminUser") == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"message\":\"Please log in first.\"}");
                return false;
            }
        }
        return true;
    }
}
