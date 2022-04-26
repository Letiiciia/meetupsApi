package com.womakerscode.microservicemeetup.model.request;

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
public class ReqRegistrationDTO {

    @NotEmpty
    private String name;

    private LocalDate dateOfRegistration;
    @NotEmpty
    private String registration;
}
