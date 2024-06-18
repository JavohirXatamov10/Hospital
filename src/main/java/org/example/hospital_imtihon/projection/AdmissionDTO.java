package org.example.hospital_imtihon.projection;
import org.example.hospital_imtihon.entity.Doctor;
import org.example.hospital_imtihon.entity.Speciality;

import java.time.LocalDateTime;
public interface AdmissionDTO {
    LocalDateTime getArrivedAt();
    LocalDateTime getAdmissionTime();
    String doctorName();
    String speciality();
    Integer getTotalSum();
}
