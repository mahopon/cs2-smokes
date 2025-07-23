package com.mahopon.cs2_smokes.config.middleware;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ReqLoggingFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String query = request.getQueryString();

        System.out.println("Incoming Request: " + method + " " + uri +
                (query != null ? "?" + query : ""));

        filterChain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Completed Request: " + method + " " + uri + " (" + duration + " ms)");
    }
}
