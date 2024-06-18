package org.example.hospital_imtihon.repo;

import org.example.hospital_imtihon.entity.Administrator;
import org.example.hospital_imtihon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    Administrator findByUser(User user);
}