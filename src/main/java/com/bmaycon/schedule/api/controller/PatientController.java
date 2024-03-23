package com.bmaycon.schedule.api.controller;

import com.bmaycon.schedule.domain.dto.request.PatientRequest;
import com.bmaycon.schedule.domain.dto.response.PatientResponse;
import com.bmaycon.schedule.domain.entity.PatientModel;
import com.bmaycon.schedule.domain.mapper.PatientMapper;
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
    private final PatientMapper mapper;

    @PostMapping
    public ResponseEntity<PatientResponse> save(@RequestBody PatientRequest request) {

        PatientModel patient = mapper.toPatientModel(request);
        PatientModel patientSaved = service.save(patient);
        PatientResponse response = mapper.toPatientResponse(patientSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> findAll() {
        List<PatientModel> patients = service.findAll();
        List<PatientResponse> response = mapper.toPatientResponseList(patients);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> findById(@PathVariable Long id) {
        Optional<PatientModel> optPatient = service.findById(id);

        if (optPatient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PatientResponse response = mapper.toPatientResponse(optPatient.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(@PathVariable Long id, @RequestBody PatientRequest request) {

        PatientModel patient = mapper.toPatientModel(request);
        patient.setId(id);

        PatientModel updatedPatient = service.update(patient);
        PatientResponse response = mapper.toPatientResponse(updatedPatient);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
