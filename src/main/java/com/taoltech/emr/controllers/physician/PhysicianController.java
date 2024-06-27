package com.taoltech.emr.controllers.physician;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taoltech.emr.EmrApplication;
import com.taoltech.emr.models.Patient;
import com.taoltech.emr.models.Role;
import com.taoltech.emr.models.User;
import com.taoltech.emr.repositories.RoleRepository;
import com.taoltech.emr.repositories.UserRepository;
import com.taoltech.emr.requests.PatientDTO;
import com.taoltech.emr.requests.PhysicianDTO;
import com.taoltech.emr.requests.UpdatePatientDTO;
import com.taoltech.emr.requests.UpdatePhysicianDTO;
import com.taoltech.emr.responses.OkResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.UUID;
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
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("physicians")
public class PhysicianController {
    
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Operation(
        tags = "Physician",
        summary = "Get Physicians list",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Physician list", 
        content = { 
            @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = OkResponse.class)
            ) 
        })
    })
    @GetMapping("{type}")
    public ResponseEntity<?> list(@PathVariable String type) {   

        Role role = roleRepository.findByName(type).orElseThrow();
        List<User> list = repository.findByRole(role);

        //
        Map<String, Object> data = new HashMap();
        data.put("physicians", list);

        //
        return ResponseEntity.ok().body(new OkResponse("Physician list", data));
    }

    // @Operation(
    //     tags = "Physician",
    //     summary = "Find a Physician",
    //     security = {
    //         @SecurityRequirement(name = EmrApplication.AUTH)
    //     }
    // )
    // @GetMapping("/{id}")
    // public ResponseEntity<?> findPhysician(@PathVariable String id) {   

    //     // Get Model
    //     User model = repository
    //         .findById(UUID.fromString(id)).orElseThrow();

    //     //
    //     Map<String, Object> data = new HashMap();
    //     data.put("physician", model);

    //     //
    //     return ResponseEntity.ok().body(new OkResponse("Physician found", data));
    // }

    @Operation(
        tags = "Physician",
        summary = "Add a new Physician",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @PostMapping("")
    public ResponseEntity<?> add(@Valid @RequestBody PhysicianDTO body) {   

        // Get Patient Role
        Role role = roleRepository
            .findByName(body.getType()).orElseThrow();

        // Create User Model
        User user = body.toUser();
        user.setRole(role);
        user.setPassword(encoder.encode(user.getPassword()));
        user = repository.save(user);

        //
        Map<String, Object> data = new HashMap();
        data.put("Physician", user);

        return ResponseEntity.ok().body(new OkResponse("Physician added", data));
    }

    @Operation(
        tags = "Physician",
        summary = "Update a Physician",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @PatchMapping("{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UpdatePhysicianDTO dto) {   

        // Create Model
        User model = repository
            .findById(UUID.fromString(id)).orElseThrow();

        //
        try {
            mapper.updateValue(model, dto);
            model = repository.save(model);

            // Update Model
            model = repository.save(model);
        } 
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update Failed");
        }

        //
        Map<String, Object> data = new HashMap();
        data.put("Physician", model);
        return ResponseEntity.ok().body(new OkResponse("Physician updated", data));
    }

    @Operation(
        tags = "Physician",
        summary = "Delete Physician",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {   

        // Find Model
        User model = repository
            .findById(UUID.fromString(id)).orElseThrow();

        // Delete model
        repository.delete(model);

        //
        Map<String, Object> data = new HashMap();
        data.put("physician", model);
        return ResponseEntity.ok().body(new OkResponse("Physician deleted", data));
    }
}
