package com.womakerscode.microservicemeetup.controller.dto.converter;

import com.womakerscode.microservicemeetup.controller.dto.RequisitionMeetupDTO;
import com.womakerscode.microservicemeetup.controller.dto.ResponseMeetupDTO;
import com.womakerscode.microservicemeetup.model.entity.Meetup;

public class MeetupToResponseMeetupDTO {
    public static ResponseMeetupDTO convert(Meetup meetup) {
        return ResponseMeetupDTO.builder()
                .id(meetup.getId())
                .meetupName(meetup.getMeetupName())
                .meetupDate(meetup.getMeetupDate())
                .registrated(meetup.isRegistrated())
                .registration(meetup.getRegistration())
                .build();
    }
    public static RequisitionMeetupDTO convertToRequisition(Meetup meetup) {
        return RequisitionMeetupDTO.builder()
                .meetupName(meetup.getMeetupName())
                .meetupDate(meetup.getMeetupDate())
                .registrated(meetup.isRegistrated())
                .registration(meetup.getRegistration())
                .build();
    }


}
