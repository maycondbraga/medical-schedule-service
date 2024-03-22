package com.bmaycon.schedule.domain.service;

import com.bmaycon.schedule.domain.entity.PatientModel;
import com.bmaycon.schedule.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;

    public PatientModel save(PatientModel patient) throws Exception {

        if (repository.existsByCpf(patient.getCpf())){
            throw new Exception("CPF already registered");
        }
        if (repository.existsByEmail(patient.getEmail())){
            throw new Exception("Email already registered");
        }

        return repository.save(patient);
    }

    public List<PatientModel> findAll() {
        return repository.findAll();
    }

    public Optional<PatientModel> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}