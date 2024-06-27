package com.taoltech.emr.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taoltech.emr.models.Sequence;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, UUID> {
    
}
