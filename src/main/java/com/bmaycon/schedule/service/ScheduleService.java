package com.bmaycon.schedule.service;

import com.bmaycon.schedule.domain.entity.ScheduleModel;
import com.bmaycon.schedule.domain.repository.ScheduleRepository;
import com.bmaycon.schedule.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;

    public List<ScheduleModel> findAll() {
        return repository.findAll();
    }

    public Optional<ScheduleModel> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new BusinessException("Schedule does not exist");
        }

        repository.deleteById(id);
    }

    public ScheduleModel save(ScheduleModel schedule) {

        Optional<ScheduleModel> optByDateTime = repository.findByDateTime(schedule.getDateTime());

        if (optByDateTime.isPresent()) {
            throw new BusinessException("There is already a schedule for this time");
        }

        schedule.setCreationDate(LocalDateTime.now());
        return repository.save(schedule);
    }

    public ScheduleModel update(ScheduleModel schedule) {

        if (!repository.existsById(schedule.getId())) {
            throw new BusinessException("Schedule does not exist");
        }

        Optional<ScheduleModel> optByDateTime = repository.findByDateTime(schedule.getDateTime());

        if (optByDateTime.isPresent() && !optByDateTime.get().getId().equals(schedule.getId())) {
            throw new BusinessException("There is already a schedule for this time");
        }

        schedule.setCreationDate(LocalDateTime.now());
        return repository.save(schedule);
    }
}
