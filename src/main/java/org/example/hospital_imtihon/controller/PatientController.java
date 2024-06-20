package org.example.hospital_imtihon.controller;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.Admission;
import org.example.hospital_imtihon.entity.Patient;
import org.example.hospital_imtihon.entity.Procedure;
import org.example.hospital_imtihon.entity.User;
import org.example.hospital_imtihon.entity.enums.Status;
import org.example.hospital_imtihon.projection.AdmissionDTO;
import org.example.hospital_imtihon.repo.ProcedureRepository;
import org.example.hospital_imtihon.service.AdmissionService;
import org.example.hospital_imtihon.service.PatientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AdmissionService admissionService;
    private final ProcedureRepository procedureRepository;
    @GetMapping
    public String sentToDoctorPage(Model model, @AuthenticationPrincipal User user){
        Patient patient =patientService.findByUser(user);
        List<Admission> allByPatientAdmission = admissionService.findAllByPatient(patient);
        allByPatientAdmission.sort(Comparator.comparing(Admission::getId).reversed());
        model.addAttribute("admissions", allByPatientAdmission);
        return "patient";
    }
    @GetMapping("getInfo/{results}")
    public String sentToInfoPage(@PathVariable String [] results, Model model){
        int patientId = Integer.parseInt(results[0]);
        int doctorId=Integer.parseInt(results[1]);
        int admissionId=Integer.parseInt(results[2]);
        List<Procedure> procedures=procedureRepository.findByPatientId(patientId,doctorId,admissionId);
        double totalSum = procedures.stream().mapToDouble(Procedure::getPrice).sum();
        model.addAttribute("totalSum",totalSum);
        model.addAttribute("procedures",procedures);
        return "getInfo";
    }
}
