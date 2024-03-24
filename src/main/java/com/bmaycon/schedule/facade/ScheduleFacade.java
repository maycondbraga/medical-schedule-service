package com.bmaycon.schedule.facade;

import com.bmaycon.schedule.domain.dto.request.ScheduleRequest;
import com.bmaycon.schedule.domain.dto.response.ScheduleResponse;
import com.bmaycon.schedule.domain.entity.PatientModel;
import com.bmaycon.schedule.domain.entity.ScheduleModel;
import com.bmaycon.schedule.domain.mapper.ScheduleMapper;
import com.bmaycon.schedule.exception.NotFoundException;
import com.bmaycon.schedule.service.PatientService;
import com.bmaycon.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleFacade {

    private final ScheduleService scheduleService;
    private final PatientService patientService;
    private final ScheduleMapper scheduleMapper;

    public ScheduleResponse save(ScheduleRequest request) {
        Optional<PatientModel> optPatient = patientService.findById(request.getPatientId());

        if (optPatient.isEmpty()) {
            throw new NotFoundException("Patient does not exist");
        }

        ScheduleModel schedule = scheduleMapper.toScheduleModel(request);
        schedule.setPatient(optPatient.get());

        return scheduleMapper.toScheduleResponse(scheduleService.save(schedule));
    }

    public List<ScheduleResponse> findAll() {
        List<ScheduleModel> schedules = scheduleService.findAll();
        return scheduleMapper.toScheduleResponseList(schedules);
    }

    public ScheduleResponse findById(Long id) {
        Optional<ScheduleModel> optSchedule = scheduleService.findById(id);

        if (optSchedule.isEmpty()) {
            throw new NotFoundException("Schedule does not exist");
        }

        return scheduleMapper.toScheduleResponse(optSchedule.get());
    }

    public ScheduleResponse update(Long id, ScheduleRequest request) {
        Optional<PatientModel> optPatient = patientService.findById(request.getPatientId());

        if (optPatient.isEmpty()) {
            throw new NotFoundException("Patient does not exist");
        }

        ScheduleModel schedule = scheduleMapper.toScheduleModel(request);
        schedule.setPatient(optPatient.get());
        schedule.setId(id);

        ScheduleModel updatedSchedule = scheduleService.update(schedule);
        return scheduleMapper.toScheduleResponse(updatedSchedule);
    }

    public void delete(Long id) {
        scheduleService.delete(id);
    }
}
