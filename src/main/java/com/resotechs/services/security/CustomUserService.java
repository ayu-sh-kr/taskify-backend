package com.resotechs.services.security;

import com.resotechs.entities.User;
import com.resotechs.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(userRepository.existsByEmail(username)){
            User user = userRepository.findByEmail(username);
            return new CustomUserDetails(user);
        }
        throw new RuntimeException("User not found by the: " + username);
    }
}
