package br.com.savepass.savepass.service;

import br.com.savepass.savepass.authentication.JwtTokenProvider;
import br.com.savepass.savepass.model.entity.UserEntity;
import br.com.savepass.savepass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public String loginUser(String username, String password) {
        var user = userRepository.findByUsername(username).orElse(new UserEntity());
        if (user.getPassword().equals(password)) {

            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

            return tokenProvider.generateToken(authentication);
        }
        throw new AccessDeniedException("Username or password invalid");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
