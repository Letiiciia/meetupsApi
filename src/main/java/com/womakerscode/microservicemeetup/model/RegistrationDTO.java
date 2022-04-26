package com.womakerscode.microservicemeetup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDTO {

    private Integer id;
    @NotEmpty
    private String name;

    private LocalDate dateOfRegistration;
    @NotEmpty
    private String registration;
}
