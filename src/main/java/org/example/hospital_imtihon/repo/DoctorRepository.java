package org.example.hospital_imtihon.repo;

import org.example.hospital_imtihon.entity.Doctor;
import org.example.hospital_imtihon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByUser(User user);
}