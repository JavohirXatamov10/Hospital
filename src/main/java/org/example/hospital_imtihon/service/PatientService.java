package org.example.hospital_imtihon.service;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.Patient;
import org.example.hospital_imtihon.entity.User;
import org.example.hospital_imtihon.repo.PatientRepository;
import org.example.hospital_imtihon.repo.RoleRepository;
import org.example.hospital_imtihon.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    public void save(User user) {
        User user1 = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(List.of(roleRepository.findById(1).get()))
                .build();
        userRepository.save(user1);
        Patient patient = Patient.builder()
                .user(user1)
                .build();
        patientRepository.save(patient);
    }
    public List<Patient> findAllSearching(String search) {
        try {
            int phone = Integer.parseInt(search);
            String phoneNumber=String.valueOf(phone);
            return patientRepository.findAllByPhone(phoneNumber);
        } catch (Exception e) {
            String search1 = "%" + search + "%";
            return patientRepository.findAllByFirstnameOrLastName(search1);
        }
    }
    public Patient findById(Integer id) {
        return patientRepository.findById(id).get();}
    public Patient findByUser(User user) {
        return patientRepository.findPatientByUser(user);}

    public void saveRepo(Patient patient) {
        patientRepository.save(patient);
    }
}
