package com.taoltech.emr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import com.taoltech.emr.models.Patient;
import com.taoltech.emr.models.User;


@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    Optional<Patient> findBySn(String sn);

    Optional<Patient> findByUser(User user);
} 