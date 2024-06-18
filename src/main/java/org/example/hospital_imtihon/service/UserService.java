package org.example.hospital_imtihon.service;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.User;
import org.example.hospital_imtihon.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void save(User user1) {
        userRepository.save(user1);}

    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }
}
