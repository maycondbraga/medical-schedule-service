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

        boolean existsCpf = false;
        Optional<PatientModel> optCpf = repository.findByCpf(patient.getCpf());

        if (optCpf.isPresent()) {
            if (!optCpf.get().getId().equals(patient.getId())){
                existsCpf = true;
            }
        }

        if (existsCpf){
            throw new Exception("CPF already registered");
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