package com.bmaycon.schedule.domain.repository;

import com.bmaycon.schedule.domain.entity.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {

}