package com.womakerscode.microservicemeetup.service.impl;

import com.womakerscode.microservicemeetup.controller.dto.RequisitionMeetupDTO;
import com.womakerscode.microservicemeetup.controller.dto.ResponseMeetupDTO;
import com.womakerscode.microservicemeetup.controller.dto.converter.MeetupToResponseMeetupDTO;
import com.womakerscode.microservicemeetup.controller.dto.converter.RegistrationToResponseRegistrationDTO;
import com.womakerscode.microservicemeetup.controller.dto.converter.RequisitionMeetupDTOToMeetup;
import com.womakerscode.microservicemeetup.model.entity.Meetup;
import com.womakerscode.microservicemeetup.model.entity.Registration;
import com.womakerscode.microservicemeetup.repository.MeetupRepository;
import com.womakerscode.microservicemeetup.service.MeetupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MeetupServiceImpl implements MeetupService {

    private MeetupRepository meetupRepository;

    @Override
    public Meetup createMeetup(Meetup meetup) {
        return this.meetupRepository.save(meetup);
    }

    @Override
    public Optional<Meetup> findById(Integer id) {
        return this.meetupRepository.findById(id);
    }

    @Override
    public List<ResponseMeetupDTO> listAllEvents() {
        List<Meetup> listMeetup = this.meetupRepository.findAll();

        List<ResponseMeetupDTO> listResponse = new ArrayList<>();
        for(Meetup meetup : listMeetup) {
            ResponseMeetupDTO response = ResponseMeetupDTO.builder()
                    .id(meetup.getId())
                    .meetupName(meetup.getMeetupName())
                    .meetupDate(meetup.getMeetupDate())
                    .registrated(meetup.isRegistrated())
                    .registration(meetup.getRegistration())
                    .build();
            listResponse.add(response);
        }
         
        return listResponse;

    }

    @Override
    public Object updateMeetup(RequisitionMeetupDTO requisitionMeetupDTO, Integer id) {
        Meetup meetup = this.findById(id).get();

        if(meetup == null) {
            return new Throwable("Event id not identified!");
        }
        Meetup updated = RequisitionMeetupDTOToMeetup.convert(requisitionMeetupDTO, id);

        Meetup saved = this.meetupRepository.save(updated);

        ResponseMeetupDTO responseUpdate = MeetupToResponseMeetupDTO.convert(saved);
        return responseUpdate;
    }

    @Override
    public void delete(Meetup meetup) {
        if (meetup == null || meetup.getId() == null) {
            throw new IllegalArgumentException("Meetup cannot be null");
        }
    this.meetupRepository.delete(meetup);

    }


}
