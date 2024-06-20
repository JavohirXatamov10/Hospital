package org.example.hospital_imtihon.controller;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.*;
import org.example.hospital_imtihon.entity.enums.Status;
import org.example.hospital_imtihon.repo.ProcedureRepository;
import org.example.hospital_imtihon.service.AdministratorService;
import org.example.hospital_imtihon.service.AdmissionService;
import org.example.hospital_imtihon.service.DoctorService;
import org.example.hospital_imtihon.service.PatientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdministratorService administratorService;
    private final DoctorService doctorService;
    private final AdmissionService admissionService;
    private final PatientService patientService;
    private final ProcedureRepository procedureRepository;
    @GetMapping
    public String sentToDoctorPage(Model model){
        List<Patient> patientList = patientService.findAll();
        patientList.sort(Comparator.comparing(Patient::getId).reversed());
        model.addAttribute("patients", patientList);
        return "admin";
    }
    @GetMapping("/addPage")
    public String sentToAddPatientPage(){
        return "addPatient";
    }
    @PostMapping("/add")
    public String addPatient(@ModelAttribute User user){
        patientService.save(user);
        return "redirect:/admin";
    }
    @PostMapping("/search")
    private String sendToSearch(String search,Model model){
        List<Patient> patientList=patientService.findAllSearching(search);
        model.addAttribute("patients", patientList);
        return "admin";
    }@GetMapping("patientId/{id}")
    public String sentToAdmissionPage(@PathVariable Integer id, Model model){
        Patient chosenPatient = patientService.findById(id);
        List<Doctor> doctors = doctorService.findAll();
        List<Admission >admissions= admissionService.findAllByPatient(chosenPatient);
        List<Patient> patientList = patientService.findAll();
        patientList.sort(Comparator.comparing(Patient::getId).reversed());
        model.addAttribute("currentPatient",chosenPatient);
        model.addAttribute("activePatientId", id);
        model.addAttribute("patients", patientList);
        model.addAttribute("admissions",admissions);
        model.addAttribute("doctors", doctors);
        return "admin";
    }
    @PostMapping("/subscribe")
    private String subscribe(Integer patientId, LocalDateTime admissionTime, Integer doctorId, String description, @AuthenticationPrincipal User user,Model model){
       Doctor doctor= doctorService.findById(doctorId);
       Patient patient=patientService.findById(patientId);
       Administrator administrator=administratorService.findByUser(user);
        List<Admission> admissions = admissionService.findAllByPatientId(patientId);//changes
        admissions.sort(Comparator.comparing(Admission::getAdmissionTime, Comparator.naturalOrder()));
        Admission admission= Admission.builder()
               .admissionTime(admissionTime)
                .description(description)
                .patient(patient)
                .administrator(administrator)
                .doctor(doctor)
                .status(Status.REGISTERED)
                .build();
        admissionService.save(admission);
        model.addAttribute("admissions", admissions);
        return "redirect:/admin/patientId/" + patientId;
    }
    @GetMapping("/changeStatus/{id}")
    public String changeAdmissionStatus(@PathVariable Integer id){
        Admission admission=admissionService.findById(id).get();
        admission.setArrivedAt(LocalDateTime.now());
        admission.setStatus(Status.WAITING);
        admissionService.save(admission);
        return "redirect:/admin/patientId/" + admission.getPatient().getId();
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
        return "getInfoAdmin";
    }


}
