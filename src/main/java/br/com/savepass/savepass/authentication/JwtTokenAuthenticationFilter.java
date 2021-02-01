package br.com.savepass.savepass.authentication;

import br.com.savepass.savepass.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfiguration jwtConfig;
    private JwtTokenProvider tokenProvider;
    private UserService userService;

    public JwtTokenAuthenticationFilter(
            JwtConfiguration jwtConfig,
            JwtTokenProvider tokenProvider,
            UserService userService) {

        this.jwtConfig = jwtConfig;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 1. Get the authentication header. Tokens are supposed to be passed in the authentication header
        String header = request.getHeader(jwtConfig.getHeader());

        // 2. Validate the header and check the prefix
        if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
            chain.doFilter(request, response);  		// If not valid, go to the next filter.
            return;
        }

        // 3. Get the token
        String token = header.replace(jwtConfig.getPrefix(), "");

        if(tokenProvider.validateToken(token)) {
            Claims claims = tokenProvider.getClaimsFromJWT(token);
            String username = claims.getSubject();

            var user = userService.findUserByUserName(username);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    user, null, null);
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            SecurityContextHolder.clearContext();
        }

        // Go to the next filter in the filter chain
        chain.doFilter(request, response);
    }

}
