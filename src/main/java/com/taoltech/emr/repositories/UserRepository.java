package com.taoltech.emr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import com.taoltech.emr.models.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    
}
