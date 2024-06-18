package org.example.hospital_imtihon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hospital_imtihon.entity.enums.Status;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private User user;


}