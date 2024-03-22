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

    public List<PatientModel> findAll() {
        return repository.findAll();
    }

    public Optional<PatientModel> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) throws Exception {

        if (!repository.existsById(id)) {
            throw new Exception("Patient does not exist");
        }

        repository.deleteById(id);
    }

    public PatientModel save(PatientModel patient) throws Exception {

        if (repository.existsByCpf(patient.getCpf())) {
            throw new Exception("CPF already registered");
        }
        if (repository.existsByEmail(patient.getEmail())) {
            throw new Exception("Email already registered");
        }

        return repository.save(patient);
    }

    public PatientModel update(PatientModel patient) throws Exception {

        Optional<PatientModel> optCpf = repository.findByCpf(patient.getCpf());
        Optional<PatientModel> optEmail = repository.findByEmail(patient.getEmail());

        if (optCpf.isPresent() && !optCpf.get().getId().equals(patient.getId())) {
            throw new Exception("CPF already registered");
        }
        if (optEmail.isPresent() && !optEmail.get().getId().equals(patient.getId())) {
            throw new Exception("Email already registered");
        }

        return repository.save(patient);
    }
}