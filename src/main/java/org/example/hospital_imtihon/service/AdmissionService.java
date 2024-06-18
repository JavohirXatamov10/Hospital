package org.example.hospital_imtihon.service;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.Admission;
import org.example.hospital_imtihon.entity.Doctor;
import org.example.hospital_imtihon.entity.Patient;
import org.example.hospital_imtihon.projection.AdmissionDTO;
import org.example.hospital_imtihon.repo.AdmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdmissionService {

    private final AdmissionRepository admissionRepository;
    public List<Admission> findAllByPatient(Patient patient) {
        return admissionRepository.findAllByPatient(patient);
    }
    public void save(Admission admission) {
        admissionRepository.save(admission);
    }
    public List<Admission> findAllByPatientId(Integer patientId) {
        return admissionRepository.findAllAdmissionByPatientId(patientId);}//changes
    public Optional<Admission> findById(Integer id) {
     return admissionRepository.findById(id);}
    public List<Admission> findAllByDoctor(Doctor doctor) {
        return admissionRepository.findAllByDoctor(doctor);}
    public Admission findByPatientIdAndDoctorId(Integer patient_id, Integer doctor_id) {
       return admissionRepository.findByPatientIdAndDoctorId(patient_id,doctor_id);
    }
    public List<AdmissionDTO> findAllByPatientDTO(Integer id) {
        return admissionRepository.findAllByPatientId(id);
    }
}
