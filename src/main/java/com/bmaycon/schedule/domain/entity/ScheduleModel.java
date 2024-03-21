package com.bmaycon.schedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "schedule")
public class ScheduleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne
    private PatientModel patient;
}
