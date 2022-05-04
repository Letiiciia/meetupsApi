package com.womakerscode.microservicemeetup.service;

import com.womakerscode.microservicemeetup.controller.dto.RequisitionMeetupDTO;
import com.womakerscode.microservicemeetup.controller.dto.ResponseMeetupDTO;
import com.womakerscode.microservicemeetup.model.entity.Meetup;

import java.util.List;
import java.util.Optional;

public interface MeetupService {
    Meetup createMeetup(Meetup meetup);

    Optional<Meetup> findById(Integer id);

    List<ResponseMeetupDTO> listAllEvents();

    Object updateMeetup(RequisitionMeetupDTO requisitionMeetupDTO, Integer id);

    void delete(Meetup meetup);

}
