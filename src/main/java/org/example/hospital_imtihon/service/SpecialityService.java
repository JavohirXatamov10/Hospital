package org.example.hospital_imtihon.service;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.Speciality;
import org.example.hospital_imtihon.repo.SpecialityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialityService {
    private final SpecialityRepository specialityRepository;
    public List<Speciality> findAll() {
        return  specialityRepository.findAll();}
    public void save(Speciality speciality1) {
        specialityRepository.save(speciality1);}
    public Speciality findById(Integer specialityId) {
        return specialityRepository.findById(specialityId).get();}
}
