package org.example.hospital_imtihon.repo;

import org.example.hospital_imtihon.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
    @Query(value = "select p.* from procedure p join public.admission_procedure_list apl on p.id = apl.procedure_list_id where admission_id=?1;",nativeQuery = true)
    List<Procedure> findByAdmission(Integer admission);
    @Query(value = """
     select p.* from procedure p 
    join admission_procedure_list apl on p.id = apl.procedure_list_id 
    join admission a on a.id = apl.admission_id 
    where a.patient_id = ?1 and a.doctor_id=?2 and admission_id=?3
    """, nativeQuery = true)
    List<Procedure> findByPatientId(Integer id, int doctorId, int admissionId);
}