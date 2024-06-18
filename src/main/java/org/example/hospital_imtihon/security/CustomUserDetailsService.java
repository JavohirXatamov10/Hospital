package org.example.hospital_imtihon.security;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails byPhone = userRepository.findByPhone(username);
        System.out.println(byPhone);
        return byPhone;
    }

}
