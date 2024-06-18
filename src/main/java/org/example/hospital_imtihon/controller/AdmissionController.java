package org.example.hospital_imtihon.controller;
import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.Doctor;
import org.example.hospital_imtihon.entity.Patient;
import org.example.hospital_imtihon.service.AdministratorService;
import org.example.hospital_imtihon.service.DoctorService;
import org.example.hospital_imtihon.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
@Controller
@RequestMapping("/admission")
@RequiredArgsConstructor
public class AdmissionController {
    private final PatientService patientService;
    private final AdministratorService administratorService;
    private final DoctorService doctorService;
//    @GetMapping
//    public String sendToAdmission(Model model){
//        List<Patient> patientList = patientService.findAll();
//        patientList.sort(Comparator.comparing(Patient::getId).reversed());
//        List<Doctor> doctors = doctorService.findAll();
//        model.addAttribute("doctors", doctors);
//        model.addAttribute("patients", patientList);
//        return "admission";
//    }
//
//    @GetMapping("patientId/{id}")
//    public String sentToAdmissionPage(@PathVariable Integer id, Model model){
//        List<Patient> patientList = patientService.findAll();
//        patientList.sort(Comparator.comparing(Patient::getId).reversed());
//        Patient patient = patientService.findById(id);
//
//        List<Doctor> doctors = doctorService.findAll();
//        model.addAttribute("currentPatient", patient);
//        model.addAttribute("doctors", doctors);
//        model.addAttribute("patients", patientList);
//        return "admission";
//    }
//
//    @PostMapping("/search")
//    private String sendToSearch(@RequestParam("search") String search, Model model){
//        List<Patient> patientList = patientService.findAllSearching(search);
//        model.addAttribute("patients", patientList);
//        return "admission"; // Ensure this is the correct view name
//    }


//    private final PatientService patientService;
//    private final AdministratorService administratorService;
//    private final DoctorService doctorService;
//    @GetMapping("patientId/{id}")
//    public String sentToAdmissionPage(@PathVariable Integer id, Model model){
//        List<Patient> patientList=patientService.findAll();
//        patientList.sort(Comparator.comparing(Patient::getId).reversed());
//        Patient patient =patientService.findById(id);
//        List<Doctor> doctors=doctorService.findAll();
//        model.addAttribute("currentPatient",patient);
//        model.addAttribute("doctors", doctors);
//        model.addAttribute("patients",patientList);
//        return "admission";
//    }
//    @PostMapping("/search")
//    private String sendToSearch(String search,Model model){
//        List<Patient> patientList=patientService.findAllSearching(search);
//        model.addAttribute("patients", patientList);
//        return "admin";
//    }
}
