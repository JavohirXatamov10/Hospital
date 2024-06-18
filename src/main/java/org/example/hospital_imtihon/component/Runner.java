package org.example.hospital_imtihon.component;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.*;
import org.example.hospital_imtihon.entity.enums.RoleUser;
import org.example.hospital_imtihon.entity.enums.Status;
import org.example.hospital_imtihon.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final AdministratorRepository administratorRepository;
    private final DoctorRepository doctorRepository;
    private final RoleRepository roleRepository;
    private final RoomRepository roomRepository;
    private final SpecialityRepository specialityRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        //getInfo();
    }
    private void getInfo() {
        Room room1= Room.builder()
                .name("1-xona")
                .build();
        Room room2= Room.builder()
                .name("2-xona")
                .build();
        Room room3= Room.builder()
                .name("3-xona")
                .build();
        Room room4= Room.builder()
                .name("4-xona")
                .build();
        Room room5= Room.builder()
                .name("5-xona")
                .build();
        Room room6= Room.builder()
                .name("6-xona")
                .build();
        Room room7= Room.builder()
                .name("7-xona")
                .build();
        Room room8= Room.builder()
                .name("8-xona")
                .build();
        Room room9= Room.builder()
                .name("8-xona")
                .build();
        Room room10= Room.builder()
                .name("10-xona")
                .build();
       roomRepository.save(room1);
       roomRepository.save(room2);
       roomRepository.save(room3);
       roomRepository.save(room4);
       roomRepository.save(room5);
       roomRepository.save(room6);
       roomRepository.save(room7);
       roomRepository.save(room8);
       roomRepository.save(room9);
       roomRepository.save(room10);

        Speciality speciality1= Speciality.builder()
                .name("Stomotolog")
                .build();
        Speciality speciality2= Speciality.builder()
                .name("Lor")
                .build();
        Speciality speciality3= Speciality.builder()
                .name("Terapevt")
                .build();
        Speciality speciality4= Speciality.builder()
                .name("Bolalar shifokori")
                .build();
        specialityRepository.save(speciality1);
        specialityRepository.save(speciality2);
        specialityRepository.save(speciality3);
        specialityRepository.save(speciality4);

        Role role1= Role.builder().roleUser(RoleUser.ROLE_PATIENT).build();
        Role role2= Role.builder().roleUser(RoleUser.ROLE_ADMIN).build();
        Role role3= Role.builder().roleUser(RoleUser.ROLE_DOCTOR).build();
        Role role4= Role.builder().roleUser(RoleUser.ROLE_SUPER_ADMIN).build();

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
        roleRepository.save(role4);

        User user1=User.builder()
                .firstName("Toshmat")
                .lastName("Hikmatov")
                .phone("7654321")
                .password(passwordEncoder.encode("toshmat1234"))
                .roles(List.of(role1))
                .build();

        User user2=User.builder()
                .firstName("Hikmat")
                .lastName("Toshmatov")
                .phone("1234567")
                .password(passwordEncoder.encode("hikmat1234"))
                .roles(List.of(role1))
                .build();

        User user3=User.builder()
                .firstName("Toshmat")
                .lastName("Toshmatov")
                .phone("901234566")
                .password(passwordEncoder.encode("root1234"))
                .roles(List.of(role1))
                .build();

        User user4=User.builder()
                .firstName("Islom")
                .lastName("Fazyziyev")
                .phone("973291316")
                .password(passwordEncoder.encode("islom1234"))
                .roles(List.of(role3))
                .build();

        User user5=User.builder()
                .firstName("Bunyod")
                .lastName("Hamraqulov")
                .phone("932920304")
                .password(passwordEncoder.encode("bunyod1234"))
                .roles(List.of(role3))
                .build();

        User user6=User.builder()
                .firstName("Javohir")
                .lastName("Rahmatullayev")
                .phone("932283899")
                .password(passwordEncoder.encode("javohir1234"))
                .roles(List.of(role3))
                .build();

        User user7=User.builder()
                .firstName("Boymurod")
                .lastName("Xoliqov")
                .phone("993216549")
                .password(passwordEncoder.encode("boymurod1234"))
                .roles(List.of(role2)).build();

        User user8=User.builder()
                .firstName("Javohir")
                .lastName("Hatamov")
                .phone("905166561")
                .password(passwordEncoder.encode("root123"))
                .roles(List.of(role2,role4))
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);

        Patient patient1= Patient.builder().user(user1).build();
        Patient patient2= Patient.builder().user(user2).build();
        Patient patient3= Patient.builder().user(user3).build();

        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);

        Doctor  doctor1= Doctor.builder().user(user4).speciality(speciality3).roomNumber(room1).build();
        Doctor  doctor2= Doctor.builder().user(user5).speciality(speciality2).roomNumber(room2).build();
        Doctor  doctor3= Doctor.builder().user(user6).speciality(speciality3).roomNumber(room3).build();
        Administrator administrator2= Administrator.builder().user(user7).build();
        Administrator administrator1= Administrator.builder().user(user8).build();

        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);
        doctorRepository.save(doctor3);

        administratorRepository.save(administrator1);
        administratorRepository.save(administrator2);





    }
}
