package com.bmaycon.schedule.domain.mapper;

import com.bmaycon.schedule.domain.dto.request.PatientRequest;
import com.bmaycon.schedule.domain.dto.response.PatientResponse;
import com.bmaycon.schedule.domain.entity.PatientModel;

import java.util.ArrayList;
import java.util.List;

public class PatientMapper {
    public static PatientModel toPatientModel(PatientRequest request) {
        PatientModel patient = new PatientModel();
        patient.setName(request.getName());
        patient.setSurname(request.getSurname());
        patient.setCpf(request.getCpf());
        patient.setEmail(request.getEmail());
        return patient;
    }

    public static PatientResponse toPatientResponse(PatientModel patient) {
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setName(patient.getName());
        response.setSurname(patient.getSurname());
        response.setCpf(patient.getCpf());
        response.setEmail(patient.getEmail());
        return response;
    }

    public static List<PatientResponse> toPatientResponseList(List<PatientModel> patients){
        List<PatientResponse> responses = new ArrayList<>();
        for (PatientModel patient : patients){
            responses.add(toPatientResponse(patient));
        }
        return responses;
    }
}
