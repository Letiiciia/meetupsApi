package com.womakerscode.microservicemeetup.controller.dto.converter;

import com.womakerscode.microservicemeetup.controller.dto.RequisitionMeetupDTO;
import com.womakerscode.microservicemeetup.model.entity.Meetup;

public class RequisitionMeetupDTOToMeetup {
    public static Meetup convert(RequisitionMeetupDTO requisitionMeetupDTO, Integer id) {
        return Meetup.builder()
                .id(id)
                .meetupName(requisitionMeetupDTO.getMeetupName())
                .meetupDate(requisitionMeetupDTO.getMeetupDate())
                .registrated(requisitionMeetupDTO.isRegistrated())
                .registration(requisitionMeetupDTO.getRegistration())
                .build();
    }
}
