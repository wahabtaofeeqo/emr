package com.taoltech.emr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import com.taoltech.emr.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
} 