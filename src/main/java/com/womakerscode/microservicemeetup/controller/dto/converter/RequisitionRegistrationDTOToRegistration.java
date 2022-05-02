package com.womakerscode.microservicemeetup.controller.dto.converter;

import com.womakerscode.microservicemeetup.controller.dto.RequisitionRegistrationDTO;
import com.womakerscode.microservicemeetup.model.entity.Meetup;
import com.womakerscode.microservicemeetup.model.entity.Registration;

public class RequisitionRegistrationDTOToRegistration {

    public static Registration convert(RequisitionRegistrationDTO requisitionRegistrationDTO, Meetup meetup) {
        return Registration.builder()
                .name(requisitionRegistrationDTO.getName())
                .dateOfRegistration(requisitionRegistrationDTO.getDateOfRegistration())
                .registration(requisitionRegistrationDTO.getRegistration())
                .meetup(meetup)
                .build();
    }
}
