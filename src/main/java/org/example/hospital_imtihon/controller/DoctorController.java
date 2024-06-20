package org.example.hospital_imtihon.controller;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.*;
import org.example.hospital_imtihon.entity.enums.Status;
import org.example.hospital_imtihon.repo.ProcedureRepository;
import org.example.hospital_imtihon.service.AdmissionService;
import org.example.hospital_imtihon.service.DoctorService;
import org.example.hospital_imtihon.service.PatientService;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final AdmissionService admissionService;
    private final PatientService patientService;
    private final ProcedureRepository procedureRepository;
    @GetMapping
    public String sentToDoctorPage(Model model, @AuthenticationPrincipal User user) {
        Doctor doctor = doctorService.findByUser(user);
        List<Admission> admissionList = admissionService.findAllByDoctor(doctor);
        List<Admission> listAdmission = admissionList.stream().filter(item -> item.getStatus().equals(Status.WAITING)).toList();
        model.addAttribute("doctor", doctor);
        model.addAttribute("admissions", listAdmission);
        return "doctor";
    }
//
//    @GetMapping("/doctor/patient/{id}")
//    public String getPatient(@PathVariable Integer id, Model model, @AuthenticationPrincipal User user) {
//        Doctor doctor = doctorService.findByUser(user);
//        Admission admission = admissionService.findByPatientIdAndDoctorId(id, doctor.getId());
//        model.addAttribute("currentPatient", admission.getPatient());
//        model.addAttribute("admissions", admissionService.findAllByDoctor(doctor));
//        model.addAttribute("procedures", procedureRepository.findByAdmission(admission.getId()));
//        return "doctor";
//    }










    @GetMapping("patient/{id}")
    public String sentToAdmissionPage(@PathVariable Integer id, Model model, @AuthenticationPrincipal User user) {
        Patient chosenPatient = patientService.findById(id);
        Doctor doctor = doctorService.findByUser(user);
        //Admission admission=admissionService.findByPatientIdAndDoctorId(chosenPatient.getId(), doctor.getId());
        Admission admission=admissionService.findByPatientIdAndDoctorId(chosenPatient.getId(), doctor.getId());

        List<Admission> admissions  = admissionService.findAllByDoctor(doctor);
        List<Procedure> procedures=procedureRepository.findByAdmission(admission.getId());

        long totalSum = procedures.stream().mapToInt(Procedure::getPrice).sum();
        model.addAttribute("totalSum",totalSum);
        model.addAttribute("procedures",procedures);
        model.addAttribute("currentPatient", chosenPatient);
        model.addAttribute("doctor",doctor);
        model.addAttribute("activePatientId", id);
        model.addAttribute("admissions", admissions);
        return "doctor";
    }

   @PostMapping("addProcedure/{id}")
    public String addProcedure(@PathVariable Integer id, Model model, @ModelAttribute Procedure procedure,@AuthenticationPrincipal User user){
       Procedure procedure1= Procedure.builder()
                .title(procedure.getTitle())
                .description(procedure.getDescription())
                .price(procedure.getPrice())
                .build();
       procedureRepository.save(procedure1);
       Doctor doctor = doctorService.findByUser(user);
       model.addAttribute("doctor",doctor);
       Admission admission= admissionService.findByPatientIdAndDoctorId(id,doctor.getId());
       admission.getProcedureList().add(procedure1);
       admissionService.save(admission);
       return "redirect:/doctor/patient/"+id;
    }

    @GetMapping("/finishProcedure/{id}")
    public String finishProcedure(@PathVariable Integer id,@AuthenticationPrincipal User user, Model model){
        Doctor doctor = doctorService.findByUser(user);
        Admission admission=admissionService.findByPatientIdAndDoctorId(id, doctor.getId());
        admission.setStatus(Status.COMPLETED);
        admissionService.save(admission);
        model.addAttribute("doctor",doctor);
        return "redirect:/doctor";
    }



}
