package org.example.hospital_imtihon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hospital_imtihon.entity.enums.RoleUser;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    @Override
    public String getAuthority() {
        return this.roleUser.name();
    }
}