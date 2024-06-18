package org.example.hospital_imtihon.service;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.Doctor;
import org.example.hospital_imtihon.entity.User;
import org.example.hospital_imtihon.repo.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public List<Doctor> findAll() {
        return doctorRepository.findAll();}
    public Doctor findById(Integer doctorId) {
         return doctorRepository.findById(doctorId).get();
    }
    public Doctor findByUser(User user) {
        return doctorRepository.findByUser(user);
    }
    public void save(Doctor doctor) {doctorRepository.save(doctor);}
}
