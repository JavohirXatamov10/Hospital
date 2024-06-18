package org.example.hospital_imtihon.repo;

import org.example.hospital_imtihon.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
}