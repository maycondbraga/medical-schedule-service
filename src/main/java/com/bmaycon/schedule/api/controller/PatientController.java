package com.bmaycon.schedule.api.controller;

import com.bmaycon.schedule.domain.entity.PatientModel;
import com.bmaycon.schedule.domain.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService service;

    @PostMapping
    public ResponseEntity<PatientModel> save(@RequestBody PatientModel patient) {
        PatientModel patientSaved = service.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientSaved);
    }

    @GetMapping
    public ResponseEntity<List<PatientModel>> findAll() {
        List<PatientModel> patients = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientModel> findById(@PathVariable Long id) {
        Optional<PatientModel> optPatient = service.findById(id);

        if (optPatient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(optPatient.get());
    }

    @PutMapping
    public ResponseEntity<PatientModel> update(@RequestBody PatientModel patient) {
        PatientModel updatedPatient = service.update(patient);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
