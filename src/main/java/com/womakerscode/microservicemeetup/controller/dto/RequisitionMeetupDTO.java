package com.womakerscode.microservicemeetup.controller.dto;

import com.womakerscode.microservicemeetup.model.entity.Registration;
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
public class RequisitionMeetupDTO {

    @NotEmpty
    private String meetupName;

    private LocalDate meetupDate;

    @NotEmpty
    private boolean registrated;

    private Registration registration;

}
