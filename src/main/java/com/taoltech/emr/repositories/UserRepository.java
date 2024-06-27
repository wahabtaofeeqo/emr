package com.taoltech.emr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import com.taoltech.emr.models.Role;
import com.taoltech.emr.models.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByRole(Role role);

    Optional<User> findByPhoneOrEmail(String phone, String email);
}
