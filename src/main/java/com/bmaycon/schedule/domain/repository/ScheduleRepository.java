package com.bmaycon.schedule.domain.repository;

import com.bmaycon.schedule.domain.entity.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleModel, Long> {
    List<ScheduleModel> findAllByOrderById();
    Optional<ScheduleModel> findByDateTime(LocalDateTime dateTime);
}