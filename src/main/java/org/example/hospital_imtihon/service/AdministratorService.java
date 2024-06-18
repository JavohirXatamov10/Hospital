package org.example.hospital_imtihon.service;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.Administrator;
import org.example.hospital_imtihon.entity.Admission;
import org.example.hospital_imtihon.entity.Patient;
import org.example.hospital_imtihon.entity.User;
import org.example.hospital_imtihon.repo.AdministratorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministratorService {
    private final AdministratorRepository administratorRepository;
    public Administrator findByUser(User user) {
        return administratorRepository.findByUser(user);
    }
}
