package com.bmaycon.schedule.domain.repository;

import com.bmaycon.schedule.domain.entity.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {
    List<PatientModel> findAllByOrderById();
    Optional<PatientModel> findByCpf(String cpf);
    Optional<PatientModel> findByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}