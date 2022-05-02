package com.womakerscode.microservicemeetup.service.impl;

import com.womakerscode.microservicemeetup.controller.dto.ResponseMeetupDTO;
import com.womakerscode.microservicemeetup.controller.dto.ResponseRegistrationDTO;
import com.womakerscode.microservicemeetup.controller.dto.converter.RegistrationToResponseRegistrationDTO;
import com.womakerscode.microservicemeetup.exception.BusinessException;
import com.womakerscode.microservicemeetup.model.entity.Meetup;
import com.womakerscode.microservicemeetup.model.entity.Registration;
import com.womakerscode.microservicemeetup.repository.RegistrationRepository;
import com.womakerscode.microservicemeetup.service.MeetupService;
import com.womakerscode.microservicemeetup.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    RegistrationRepository repository;
    MeetupService meetupService;


    public String save(Registration registration) {
        if (repository.existsByRegistration(registration.getRegistration())) {
            throw new BusinessException("Registration already created");
        }
        Meetup meetup = registration.getMeetup();
        meetup.getRegistration().add(registration);
        this.meetupService.createMeetup(meetup);

        return "Created " + registration.getName();

    }

    @Override
    public Optional<Registration> getRegistrationById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(Registration registration) {
        if (registration == null || registration.getId() == null) {
            throw new IllegalArgumentException("Registration id cannot be null");
        }
        this.repository.delete(registration);
    }

    @Override
    public Registration update(Registration registration) {
        if (registration == null || registration.getId() == null) {
            throw new IllegalArgumentException("Registration id cannot be null");
        }

        return this.repository.save(registration);
    }

    @Override
    public List<ResponseRegistrationDTO> listAllRegistrations() {
        List<Registration> list = this.repository.findAll();

        List<ResponseRegistrationDTO> listResponse = new ArrayList<>();
        for(Registration registration : list) {
            ResponseRegistrationDTO response = ResponseRegistrationDTO.builder()
                    .id(registration.getId())
                    .name(registration.getName())
                    .dateOfRegistration(registration.getDateOfRegistration())
                    .meetup(RegistrationToResponseRegistrationDTO.buildMeetup(registration.getMeetup()))
                    .registration(registration.getRegistration())
                    .build();
            listResponse.add(response);
        }

        return listResponse;

    }

//    @Override
//    public Page<Registration> find(Registration filter, Pageable pageRequest) {
//        Example<Registration> example = Example.of(filter,
//                ExampleMatcher
//                        .matching()
//                        .withIgnoreCase()
//                        .withIgnoreNullValues()
//                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
//        return repository.findAll(example, pageRequest);



    @Override
    public Optional<Registration> getRegistrationByRegistrationAttribute(String registrationAttribute) {
        return this.repository.findByRegistration(registrationAttribute);
    }
}
