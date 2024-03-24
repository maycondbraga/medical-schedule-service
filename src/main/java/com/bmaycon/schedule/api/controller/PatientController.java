package com.bmaycon.schedule.api.controller;

import com.bmaycon.schedule.domain.dto.request.PatientRequest;
import com.bmaycon.schedule.domain.dto.response.PatientResponse;
import com.bmaycon.schedule.facade.PatientFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientFacade patientFacade;

    @PostMapping
    public ResponseEntity<PatientResponse> save(@Valid @RequestBody PatientRequest request) {
        PatientResponse response = patientFacade.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> findAll() {
        List<PatientResponse> responses = patientFacade.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> findById(@PathVariable Long id) {
        PatientResponse response = patientFacade.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(@PathVariable Long id, @Valid @RequestBody PatientRequest request) {
        PatientResponse response = patientFacade.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientFacade.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
