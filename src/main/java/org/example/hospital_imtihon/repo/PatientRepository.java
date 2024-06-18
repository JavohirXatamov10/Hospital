package org.example.hospital_imtihon.repo;

import org.example.hospital_imtihon.entity.Patient;
import org.example.hospital_imtihon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "SELECT p.* FROM patient p JOIN public.users u ON u.id = p.user_id WHERE u.phone LIKE ?1", nativeQuery = true)
    List<Patient> findAllByPhone(String phoneNumber);
    @Query(value = "SELECT p.* FROM patient p JOIN public.users u ON u.id = p.user_id WHERE u.first_name ILIKE ?1 OR u.last_name ILIKE ?1", nativeQuery = true)
    List<Patient> findAllByFirstnameOrLastName( String search);
    Patient findPatientByUser(User user);
}