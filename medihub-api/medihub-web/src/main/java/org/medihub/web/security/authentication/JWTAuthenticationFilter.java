package org.medihub.web.security.authentication;

import lombok.RequiredArgsConstructor;
import org.medihub.web.security.TokenUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final TokenUtil tokenUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println(httpServletRequest.getRequestURI());
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        validateAuthorizationHeader(authorizationHeader);

        final String token = authorizationHeader.substring(7);
        final UserDetails userDetails = getUserDetails(token);
        validateToken(token, userDetails);

        setUpAuthentication(token, userDetails);
    }

    private void validateAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null || authorizationHeader.startsWith("Bearer ")) {
            // throw exception
        }
    }

    private UserDetails getUserDetails(String token) {
        final String username = tokenUtil.getUsernameFromToken(token);
        return userDetailsService.loadUserByUsername(username);
    }

    private void validateToken(String token, UserDetails userDetails) {
        if (!tokenUtil.validateToken(token, userDetails)) {
            // throw exception
        }
    }

    private void setUpAuthentication(String token, UserDetails userDetails) {
        TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
        authentication.setToken(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
