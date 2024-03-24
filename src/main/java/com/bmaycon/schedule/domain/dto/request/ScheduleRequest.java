package com.bmaycon.schedule.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {

    @NotBlank(message = "Description is required")
    private String description;

    @Future
    @NotNull(message = "Date time is required")
    private LocalDateTime dateTime;

    @NotNull(message = "Patient identifier is required")
    private Long patientId;
}
