package com.bmaycon.schedule.facade;

import com.bmaycon.schedule.domain.dto.request.PatientRequest;
import com.bmaycon.schedule.domain.dto.response.PatientResponse;
import com.bmaycon.schedule.domain.entity.PatientModel;
import com.bmaycon.schedule.domain.mapper.PatientMapper;
import com.bmaycon.schedule.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientFacade {

    private final PatientService service;
    private final PatientMapper mapper;

    public PatientResponse save(PatientRequest request) {
        PatientModel patient = mapper.toPatientModel(request);
        PatientModel patientSaved = service.save(patient);
        return mapper.toPatientResponse(patientSaved);
    }

    public List<PatientResponse> findAll() {
        List<PatientModel> patients = service.findAll();
        return mapper.toPatientResponseList(patients);
    }

    public PatientResponse findById(Long id) {
        PatientModel patient = service.findById(id);
        return mapper.toPatientResponse(patient);
    }

    public PatientResponse update(Long id, PatientRequest request) {
        PatientModel patient = mapper.toPatientModel(request);
        patient.setId(id);

        PatientModel updatedPatient = service.update(patient);
        return mapper.toPatientResponse(updatedPatient);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}