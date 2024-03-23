package com.bmaycon.schedule.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "surname is required")
    private String surname;

    @NotBlank(message = "CPF is required")
    private String cpf;

    @Email
    @NotBlank(message = "email is required")
    private String email;
}
