package org.example.hospital_imtihon.service;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.Role;
import org.example.hospital_imtihon.entity.Room;
import org.example.hospital_imtihon.repo.RoleRepository;
import org.example.hospital_imtihon.repo.RoomRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role findById(int id) {
        return roleRepository.findById(id).get();
    }
}
