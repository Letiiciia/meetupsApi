package com.womakerscode.microservicemeetup.controller.dto;

import com.womakerscode.microservicemeetup.model.entity.Meetup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequisitionRegistrationDTO {

    @NotEmpty
    private String name;

    private LocalDate dateOfRegistration;
    @NotEmpty
    private String nickName;

    private Integer meetupId;
}
