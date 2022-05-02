package com.womakerscode.microservicemeetup.controller.dto.converter;

import com.womakerscode.microservicemeetup.controller.dto.ResponseMeetupDTO;
import com.womakerscode.microservicemeetup.controller.dto.ResponseRegistrationDTO;
import com.womakerscode.microservicemeetup.model.entity.Meetup;
import com.womakerscode.microservicemeetup.model.entity.Registration;

public class RegistrationToResponseRegistrationDTO {
    public static ResponseRegistrationDTO convert(Registration registration) {
        return ResponseRegistrationDTO.builder()
                .id(registration.getId())
                .name(registration.getName())
                .dateOfRegistration(registration.getDateOfRegistration())
                .registration(registration.getRegistration())
                .meetup(buildMeetup(registration.getMeetup()))
                .build();
    }

    public static ResponseMeetupDTO buildMeetup(Meetup meetup) {
        return ResponseMeetupDTO.builder()
                .id(meetup.getId())
                .meetupName(meetup.getMeetupName())
                .meetupDate(meetup.getMeetupDate())
                .registrated(meetup.isRegistrated())
                .build();
    }
}
