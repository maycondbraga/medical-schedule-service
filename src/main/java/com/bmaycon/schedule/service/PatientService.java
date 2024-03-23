package com.bmaycon.schedule.service;

import com.bmaycon.schedule.domain.entity.PatientModel;
import com.bmaycon.schedule.domain.repository.PatientRepository;
import com.bmaycon.schedule.exception.BusinessException;
import com.bmaycon.schedule.exception.NotFoundException;
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

    public PatientModel findById(Long id) {
        Optional<PatientModel> optPatient = repository.findById(id);

        if (optPatient.isEmpty()) {
            throw new NotFoundException("Patient does not exist");
        }

        return optPatient.get();
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new BusinessException("Patient does not exist");
        }

        repository.deleteById(id);
    }

    public PatientModel save(PatientModel patient) {

        if (repository.existsByCpf(patient.getCpf())) {
            throw new BusinessException("CPF already registered");
        }
        if (repository.existsByEmail(patient.getEmail())) {
            throw new BusinessException("Email already registered");
        }

        return repository.save(patient);
    }

    public PatientModel update(PatientModel patient) {

        if (!repository.existsById(patient.getId())) {
            throw new BusinessException("Patient does not exist");
        }

        Optional<PatientModel> optCpf = repository.findByCpf(patient.getCpf());
        Optional<PatientModel> optEmail = repository.findByEmail(patient.getEmail());

        if (optCpf.isPresent() && !optCpf.get().getId().equals(patient.getId())) {
            throw new BusinessException("CPF already registered");
        }
        if (optEmail.isPresent() && !optEmail.get().getId().equals(patient.getId())) {
            throw new BusinessException("Email already registered");
        }

        return repository.save(patient);
    }
}