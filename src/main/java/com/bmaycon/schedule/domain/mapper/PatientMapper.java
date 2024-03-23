package com.bmaycon.schedule.domain.mapper;

import com.bmaycon.schedule.domain.dto.request.PatientRequest;
import com.bmaycon.schedule.domain.dto.response.PatientResponse;
import com.bmaycon.schedule.domain.entity.PatientModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PatientMapper {

    private final ModelMapper mapper;

    public PatientModel toPatientModel(PatientRequest request) {
        return mapper.map(request, PatientModel.class);
    }

    public PatientResponse toPatientResponse(PatientModel patient) {
        return mapper.map(patient, PatientResponse.class);
    }

    public List<PatientResponse> toPatientResponseList(List<PatientModel> patients) {
        return patients.stream()
                .map(this::toPatientResponse)
                .collect(Collectors.toList());
    }
}