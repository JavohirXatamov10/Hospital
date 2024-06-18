package org.example.hospital_imtihon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hospital_imtihon.entity.enums.Status;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    private Patient patient;
    private String description;
    @ManyToOne
    private Doctor doctor;
    private LocalDateTime admissionTime;
    private LocalDateTime arrivedAt;
    @OneToMany
    private List<Procedure> procedureList;
    @ManyToOne
    private Administrator administrator;
    @Enumerated(EnumType.STRING)
    private Status status;
}