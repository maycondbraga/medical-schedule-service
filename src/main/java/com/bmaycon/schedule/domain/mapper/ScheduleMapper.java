package com.bmaycon.schedule.domain.mapper;

import com.bmaycon.schedule.domain.dto.request.ScheduleRequest;
import com.bmaycon.schedule.domain.dto.response.ScheduleResponse;
import com.bmaycon.schedule.domain.entity.ScheduleModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    private final ModelMapper mapper;

    public ScheduleModel toScheduleModel(ScheduleRequest request) {
        return mapper.map(request, ScheduleModel.class);
    }

    public ScheduleResponse toScheduleResponse(ScheduleModel schedule) {
        return mapper.map(schedule, ScheduleResponse.class);
    }

    public List<ScheduleResponse> toScheduleResponseList(List<ScheduleModel> schedules) {
        return schedules.stream()
                .map(this::toScheduleResponse)
                .collect(Collectors.toList());
    }
}