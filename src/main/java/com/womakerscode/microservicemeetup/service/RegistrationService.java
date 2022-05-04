package com.womakerscode.microservicemeetup.service;

import com.womakerscode.microservicemeetup.controller.dto.ResponseRegistrationDTO;
import com.womakerscode.microservicemeetup.model.entity.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface RegistrationService {

    String save(Registration any);

    Optional<Registration> getRegistrationById(Integer id);

    void delete(Registration registration);

    Registration update(Registration registration);

    List<ResponseRegistrationDTO> listAllRegistrations();

    Page<Registration> find(Registration registration, Pageable pageRequest);

    Optional<Registration> getRegistrationByRegistrationAttribute(String registrationAttribute);
}
