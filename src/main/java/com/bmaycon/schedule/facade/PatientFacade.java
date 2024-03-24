package com.bmaycon.schedule.facade;

import com.bmaycon.schedule.domain.dto.request.PatientRequest;
import com.bmaycon.schedule.domain.dto.response.PatientResponse;
import com.bmaycon.schedule.domain.entity.PatientModel;
import com.bmaycon.schedule.domain.mapper.PatientMapper;
import com.bmaycon.schedule.exception.NotFoundException;
import com.bmaycon.schedule.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientFacade {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    public PatientResponse save(PatientRequest request) {
        PatientModel patient = patientMapper.toPatientModel(request);
        PatientModel patientSaved = patientService.save(patient);
        return patientMapper.toPatientResponse(patientSaved);
    }

    public List<PatientResponse> findAll() {
        List<PatientModel> patients = patientService.findAll();
        return patientMapper.toPatientResponseList(patients);
    }

    public PatientResponse findById(Long id) {
        Optional<PatientModel> optPatient = patientService.findById(id);

        if (optPatient.isEmpty()) {
            throw new NotFoundException("Patient does not exist");
        }

        return patientMapper.toPatientResponse(optPatient.get());
    }

    public PatientResponse update(Long id, PatientRequest request) {
        PatientModel patient = patientMapper.toPatientModel(request);
        patient.setId(id);

        PatientModel updatedPatient = patientService.update(patient);
        return patientMapper.toPatientResponse(updatedPatient);
    }

    public void delete(Long id) {
        patientService.delete(id);
    }
}