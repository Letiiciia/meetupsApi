package com.womakerscode.microservicemeetup.controller;

import com.womakerscode.microservicemeetup.model.RegistrationDTO;
import com.womakerscode.microservicemeetup.model.entity.Registration;
import com.womakerscode.microservicemeetup.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private RegistrationService registrationService;
    private ModelMapper modelMapper;

    public RegistrationController(RegistrationService registrationService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationDTO create(@RequestBody @Valid RegistrationDTO registrationDTO) {
        Registration entity = modelMapper.map(registrationDTO, Registration.class);
        entity = registrationService.save(entity);

        return modelMapper.map(entity, RegistrationDTO.class);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistrationDTO get (@PathVariable Integer id) {

        return registrationService
                .getRegistrationById(id)
                .map(registration -> modelMapper.map(registration, RegistrationDTO.class))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }



}
