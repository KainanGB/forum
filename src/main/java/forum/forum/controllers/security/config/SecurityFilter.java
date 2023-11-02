package forum.forum.controllers.security.config;

import forum.forum.entities.UsersEntity;
import forum.forum.repositories.UsersRepository;
import forum.forum.services.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    JwtTokenService jwtTokenService;
    UsersRepository usersRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getHeader("Authorization") == null){
            filterChain.doFilter(request, response);
            return;
        }

        final String token = retrieveTokenFromHeader(request);

        final String verifiedToken = jwtTokenService.validateToken(token);

        final UsersEntity user = (UsersEntity) usersRepository.findByEmail(verifiedToken);

        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private String retrieveTokenFromHeader(HttpServletRequest request) {
        final String token = request.getHeader("Authorization");

        if(token == null) {
            throw new RuntimeException("Token not found on Authorization header");
        }

        return token.replace("Bearer", "");
    }
}
