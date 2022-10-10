package com.stefankrstikj.lotterysystem.security.jwt;

import com.stefankrstikj.lotterysystem.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserService jwtUserDetailsService;

    private final JwtTokenUtil jwtTokenUtil;


    public JwtAuthorizationFilter(JwtTokenUtil jwtTokenUtil, UserService jwtUserDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String jwtToken = request.getHeader("Authorization");
        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
            if (!jwtTokenUtil.isTokenExpired(jwtToken)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                logger.warn("JWT Token is expired");
            }
        } else {
            logger.warn("Invalid JWT Token");
        }
        chain.doFilter(request, response);
    }
}
