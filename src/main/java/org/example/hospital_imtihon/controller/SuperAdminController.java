package org.example.hospital_imtihon.controller;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.*;
import org.example.hospital_imtihon.repo.UserRepository;
import org.example.hospital_imtihon.service.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
@Controller
@RequestMapping("super_admin")
@RequiredArgsConstructor
public class SuperAdminController {
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final SpecialityService specialityService;
    private final RoomService roomService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @GetMapping()
    public String sentToSuperAdminPage() {
        return "superAdmin";
    }
    @GetMapping("/speciality")
    public String sentToSpecialityPage(Model model) {
        List<Speciality> specialities = specialityService.findAll();
        specialities.sort(Comparator.comparing(Speciality::getId));
        model.addAttribute("specialities", specialities);
        return "specialityCrud";
    }
    @GetMapping("/sendToSpecialityPage")
    public String sendToSpeciality() {
        return "addSpeciality";
    }
    @PostMapping("/addSpeciality")
    public String addSpeciality(String speciality) {
        Speciality speciality1 = Speciality.builder()
                .name(speciality)
                .build();
        specialityService.save(speciality1);
        return "redirect:/super_admin/speciality";
    }

    @GetMapping("editPage/{id}")
    public String sentToEditSpeciality(@PathVariable Integer id,Model model){
        Speciality editedSpeciality = specialityService.findById(id);
        model.addAttribute("editSpeciality",editedSpeciality);
        return "editSpeciality";
    }

    @PostMapping("editSpeciality/{id}")
    public String EditSpeciality(@PathVariable Integer id,String name){
        Speciality editedSpeciality = specialityService.findById(id);
        editedSpeciality.setName(name);
        specialityService.save(editedSpeciality);
        return "redirect:/super_admin/speciality";
    }





    @GetMapping("/doctor")
    public String sentDoctorPage(Model model) {
        List<Doctor> doctors = doctorService.findAll();
        doctors.sort(Comparator.comparing(Doctor::getId));
        model.addAttribute("doctors", doctors);
        return "doctorCrud";
    }
    @GetMapping("sendAddDoctorPage")
    public String sentToAddDoctorPage(Model model) {
        List<Room> rooms = roomService.findAll();
        List<Speciality> specialities = specialityService.findAll();
        model.addAttribute("rooms", rooms);
        model.addAttribute("specialities", specialities);
        return "addDoctor";
    }
    @PostMapping("/addDoctor")
    public String addPatient(Integer specialityId, Integer roomId, @ModelAttribute User user) {
        User user1 = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(List.of(roleService.findById(3)))
                .build();
        userService.save(user1);
        Doctor doctor = Doctor.builder()
                .roomNumber(roomService.findById(roomId))
                .speciality(specialityService.findById(specialityId))
                .user(user1)
                .build();
        doctorService.save(doctor);
        return "redirect:/super_admin/doctor";
    }
    @GetMapping("/editDoctorPage/{id}")
    public String sendToDoctorEditPage(@PathVariable Integer id, Model model){
        Doctor editedDoctor=doctorService.findById(id);
        List<Room> rooms = roomService.findAll();
        List<Speciality> specialities = specialityService.findAll();
        model.addAttribute("rooms", rooms);
        model.addAttribute("specialities", specialities);
        model.addAttribute("editedDoctor",editedDoctor);
        return "editDoctor";
    }
    @PostMapping("editDoctor/{id}")
    public String editDoctor(@PathVariable Integer id,@ModelAttribute User user,Integer specialityId,Integer roomId){

        Doctor editedDoctor = doctorService.findById(id);
        User user1=userService.findById(editedDoctor.getUser().getId());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPhone(user.getPhone());
        editedDoctor.setSpeciality(specialityService.findById(specialityId));
        editedDoctor.setRoomNumber(roomService.findById(roomId));
        editedDoctor.setUser(user1);
        doctorService.save(editedDoctor);
        return "redirect:/super_admin/doctor";
    }
    @GetMapping("/patient")
    public String sentToPatientPage(Model model) {
        List<Patient> patientList = patientService.findAll();
        model.addAttribute("patients", patientList);
        return "patientCrud";
    }
    @GetMapping("editPatientPage/{id}")
    public String sentToPatientEditPage(@PathVariable Integer id,Model model){
        Patient patient = patientService.findById(id);
        model.addAttribute("editedPatient", patient);
        return "editPatient";
    }

    @PostMapping("editPatient/{id}")
    public String editPatient(@PathVariable Integer id,@ModelAttribute User user){
        Patient patient = patientService.findById(id);
        User editedPatientUser = userService.findById(patient.getUser().getId());
        editedPatientUser.setFirstName(user.getFirstName());
        editedPatientUser.setLastName(user.getLastName());
        editedPatientUser.setPhone(user.getPhone());
        patient.setUser(editedPatientUser);
        patientService.saveRepo(patient);
        return "redirect:/super_admin/patient";
    }
}
