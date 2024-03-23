package com.bmaycon.schedule.api.controller;

import com.bmaycon.schedule.domain.dto.request.PatientRequest;
import com.bmaycon.schedule.domain.dto.response.PatientResponse;
import com.bmaycon.schedule.facade.PatientFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientFacade facade;

    @PostMapping
    public ResponseEntity<PatientResponse> save(@RequestBody PatientRequest request) {
        PatientResponse response = facade.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> findAll() {
        List<PatientResponse> patients = facade.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> findById(@PathVariable Long id) {
        PatientResponse response = facade.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(@PathVariable Long id, @RequestBody PatientRequest request) {
        PatientResponse response = facade.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
