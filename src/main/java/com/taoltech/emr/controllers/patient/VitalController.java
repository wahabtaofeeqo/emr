package com.taoltech.emr.controllers.patient;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taoltech.emr.EmrApplication;
import com.taoltech.emr.models.Patient;
import com.taoltech.emr.models.Vital;
import com.taoltech.emr.repositories.PatientRepository;
import com.taoltech.emr.repositories.VitalRepository;
import com.taoltech.emr.requests.UpdateVitalDTO;
import com.taoltech.emr.requests.VitalDTO;
import com.taoltech.emr.responses.OkResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("patients/vitals")
public class VitalController {
    
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private VitalRepository repository;

    @Autowired
    private PatientRepository patientRepository;

    @Operation(
        tags = "Patient",
        summary = "Add a Patient Vital",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @PostMapping("")
    public ResponseEntity<?> add(@Valid @RequestBody VitalDTO dto) {

        // Find patient
        Patient patient = patientRepository
            .findById(dto.getPatientId()).orElseThrow();

        // Create model
        Vital model = dto.toVital();
        model.setPatient(patient);
        model = repository.save(model);

        //
        Map<String, Object> data = new HashMap<>();
        data.put("vital", model);
        return ResponseEntity.ok().body(new OkResponse("Vital added", data));
    } 

    @Operation(
        tags = "Patient",
        summary = "Get Patient vitals",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @GetMapping("{patientId}")
    public ResponseEntity<?> listPatientVitals(@PathVariable("patientId") String id) {

        // Vitals
        Patient patient = patientRepository
            .findById(UUID.fromString(id)).orElseThrow();

        List<Vital> models = repository.findByPatient(patient);

        //
        Map<String, Object> data = new HashMap<>();
        data.put("vitals", models);
        return ResponseEntity.ok().body(new OkResponse("Patient Vitals", data));
    } 

    @Operation(
        tags = "Patient",
        summary = "Update a Vital",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @PatchMapping("{id}")
    public ResponseEntity<?> updateVital(@PathVariable("id") String id, @RequestBody UpdateVitalDTO dto) {

        // Find Model
        Vital model = repository
            .findById(UUID.fromString(id)).orElseThrow();
       
        try {
            // Prep
            mapper.updateValue(model, dto);
            model = repository.save(model);
        } 
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update Failed");
        }

        //
        Map<String, Object> data = new HashMap<>();
        data.put("vital", model);
        return ResponseEntity.ok().body(new OkResponse("Vital updated", data));
    } 

    @Operation(
        tags = "Patient",
        summary = "Delete a Vital",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> updateVital(@PathVariable("id") String id) {

        // Find Model
        Vital model = repository
            .findById(UUID.fromString(id)).orElseThrow();

        // Delete
        repository.delete(model);

        //
        Map<String, Object> data = new HashMap<>();
        data.put("vital", model);
        return ResponseEntity.ok().body(new OkResponse("Vital deleted", data));
    }
}
