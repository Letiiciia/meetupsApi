package com.womakerscode.microservicemeetup.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.womakerscode.microservicemeetup.model.entity.Registration;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMeetupDTO {

    private Integer id;

    @NotEmpty
    private String meetupName;

    private LocalDate meetupDate;

    @NotEmpty
    private boolean registrated;

    private List<Registration> registration;

}
