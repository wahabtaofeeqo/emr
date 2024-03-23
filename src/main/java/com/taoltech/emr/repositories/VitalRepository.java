package com.taoltech.emr.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.taoltech.emr.models.Patient;
import com.taoltech.emr.models.Vital;

@Repository
public interface VitalRepository extends JpaRepository<Vital, UUID> {

    List<Vital> findByPatient(Patient patient);
} 
