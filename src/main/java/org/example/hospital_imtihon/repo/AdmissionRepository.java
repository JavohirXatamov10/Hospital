package org.example.hospital_imtihon.repo;

import org.example.hospital_imtihon.entity.Admission;
import org.example.hospital_imtihon.entity.Doctor;
import org.example.hospital_imtihon.entity.Patient;
import org.example.hospital_imtihon.projection.AdmissionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Integer> {
    @Query(value = "SELECT * FROM admission WHERE patient_id = ?1 AND doctor_id = ?2", nativeQuery = true)
    Admission findByPatientIdAndDoctorId(Integer patientId, Integer doctorId);
    List<Admission> findAllByPatient(Patient patient);
    List<Admission> findAllAdmissionByPatientId(Integer patientId);
    List<Admission> findAllByDoctor(Doctor doctor);
    @Query(value = """
            select a.arrived_at as arrivedAt, a.admission_time as admissionTime,u.first_name as doctorName,s.name as speciality, sum(pr.price) as totalSum from admission a
                join public.admission_procedure_list apl on a.id = apl.admission_id
                join public.doctor d on d.id = a.doctor_id
                join users u on d.user_id=u.id join speciality s on d.speciality_id = s.id
                join procedure pr on apl.procedure_list_id = pr.id where patient_id=1
            group by a.arrived_at, a.admission_time, a.doctor_id, u.first_name, s.name; 
          
          """, nativeQuery = true)
    List<AdmissionDTO> findAllByPatientId(Integer id);

}