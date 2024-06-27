package com.taoltech.emr.controllers.patient;

import org.springframework.web.bind.annotation.RestController;

import com.taoltech.emr.EmrApplication;
import com.taoltech.emr.models.Sequence;
import com.taoltech.emr.repositories.SequenceRepository;
import com.taoltech.emr.responses.OkResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("sequences")
public class SequenceController {

    @Autowired
    private SequenceRepository repository;

    @Operation(
        tags = "Queue",
        summary = "Get Department Patient Queue",
        security = {
            @SecurityRequirement(name = EmrApplication.AUTH)
        }
    )
    @GetMapping("{department}")
    public ResponseEntity<?> list(@PathVariable String department) {   
        System.out.println(department);
        List<Sequence> models = repository.findAll();

        //
        Map<String, Object> data = new HashMap();
        data.put("queue", models);

        //
        return ResponseEntity.ok().body(new OkResponse("Queue list", data));
    }
}
