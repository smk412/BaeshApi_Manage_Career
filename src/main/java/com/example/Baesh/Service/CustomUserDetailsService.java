package com.example.Baesh.Service;

import com.example.Baesh.Entity.UserE;
import com.example.Baesh.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserE usere = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.withUsername(usere.getUserId())
                .password(usere.getPassword()) // BCrypt로 암호화된 비밀번호
                .authorities(usere.getRole()) // 역할 설정
                .build();
    }
}
