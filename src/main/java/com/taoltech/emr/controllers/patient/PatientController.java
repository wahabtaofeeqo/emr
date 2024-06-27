package com.taoltech.emr.controllers.patient;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taoltech.emr.EmrApplication;
import com.taoltech.emr.models.Patient;
import com.taoltech.emr.models.Role;
import com.taoltech.emr.models.User;
import com.taoltech.emr.repositories.PatientRepository;
import com.taoltech.emr.repositories.RoleRepository;
import com.taoltech.emr.repositories.UserRepository;
import com.taoltech.emr.requests.PatientDTO;
import com.taoltech.emr.requests.UpdatePatientDTO;
import com.taoltech.emr.responses.OkResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("patients")
public class PatientController {
    
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private PatientRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Operation(
        tags = "Patient",
        summary = "Get Patient list",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patient list", 
        content = { 
            @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = OkResponse.class)
            ) 
        })
    })
    @GetMapping("")
    public ResponseEntity<?> list() {   

        List<Patient> patients = repository.findAll();

        //
        Map<String, Object> data = new HashMap();
        data.put("patients", patients);

        //
        return ResponseEntity.ok().body(new OkResponse("Patient list", data));
    }

    @Operation(
        tags = "Patient",
        summary = "Find a Patient",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> findPatient(@PathVariable String id) {   

        // Get Model
        Patient patient = repository
            .findById(UUID.fromString(id)).orElseThrow();

        //
        Map<String, Object> data = new HashMap();
        data.put("patient", patient);

        //
        return ResponseEntity.ok().body(new OkResponse("Patient found", data));
    }

    @Operation(
        tags = "Patient",
        summary = "Search for a Patient",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @GetMapping("/search/{param}")
    public ResponseEntity<?> searchPatient(@PathVariable String param) {   
        
        // Get Model
        Patient patient = repository.findBySn(param).orElse(null);
        if(patient == null) { // Search by Phone number or Email
            User user = userRepository.findByPhoneOrEmail(param, param).orElseThrow();
            
            // Found
            patient = repository.findByUser(user).get();
        }

        //
        Map<String, Object> data = new HashMap();
        data.put("patient", patient);

        //
        return ResponseEntity.ok().body(new OkResponse("Patient found", data));
    }


    @Operation(
        tags = "Patient",
        summary = "Add a new Patient",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @PostMapping("")
    public ResponseEntity<?> add(@Valid @RequestBody PatientDTO body) {   

        // Get Patient Role
        Role role = roleRepository
            .findByName("PATIENT").orElseThrow();

        // Create User Model
        User user = body.toUser();
        user.setRole(role);
        user.setPassword(encoder.encode(user.getPassword()));
        user = userRepository.save(user);

        // Create Model
        long patientCount = repository.count();

        Patient patient = new Patient();
        patient.setUser(user);
        patient.setDob(body.getDob());
        patient.setGender(body.getGender());
        patient.setAddress(body.getAddress());
        patient.setSn("PAT" + (patientCount + 1));

        patient = repository.save(patient);

        //
        Map<String, Object> data = new HashMap();
        data.put("patient", patient);

        return ResponseEntity.ok().body(new OkResponse("Patient added", data));
    }

    @Operation(
        tags = "Patient",
        summary = "Update a Patient",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @PatchMapping("{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UpdatePatientDTO dto) {   

        // Create Model
        Patient patient = repository
            .findById(UUID.fromString(id)).orElseThrow();

        //
        User user = patient.getUser();
        try {
            mapper.updateValue(user, dto);
            mapper.updateValue(patient, dto);

            // Update User
            user = userRepository.save(user);

            // Update Patient
            patient.setUser(user);
            patient = repository.save(patient);
        } 
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update Failed");
        }

        //
        Map<String, Object> data = new HashMap();
        data.put("patient", patient);
        return ResponseEntity.ok().body(new OkResponse("Patient updated", data));
    }

    @Operation(
        tags = "Patient",
        summary = "Delete Patient",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {   

        // Find Model
        Patient patient = repository
            .findById(UUID.fromString(id)).orElseThrow();

        // Delete model
        repository.delete(patient);

        //
        Map<String, Object> data = new HashMap();
        data.put("patient", patient);
        return ResponseEntity.ok().body(new OkResponse("Patient deleted", data));
    }

}
