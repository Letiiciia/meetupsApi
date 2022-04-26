package com.womakerscode.microservicemeetup.controller;

import com.womakerscode.microservicemeetup.model.RegistrationDTO;
import com.womakerscode.microservicemeetup.model.entity.Registration;
import com.womakerscode.microservicemeetup.model.request.ReqRegistrationDTO;
import com.womakerscode.microservicemeetup.service.RegistrationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private RegistrationService registrationService;
    private ModelMapper modelMapper;

    public RegistrationController(RegistrationService registrationService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;

    }


    @ApiOperation(value = "Creating a new registration")
    @ApiResponses( value ={
            @ApiResponse(code = 201, message = "Registration created with success"),
            @ApiResponse(code = 400, message = "There is any information invalid in the body"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationDTO create(@RequestBody @Valid ReqRegistrationDTO reqRegistrationDTO) {
        Registration entity = modelMapper.map(reqRegistrationDTO, Registration.class);
        entity = registrationService.save(entity);

        return modelMapper.map(entity, RegistrationDTO.class);
    }

    @ApiOperation(value = "Getting a specific registration by id")
    @ApiResponses( value ={
            @ApiResponse(code = 201, message = "Getting an registration with success"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistrationDTO get (@PathVariable Integer id) {

        return registrationService
                .getRegistrationById(id)
                .map(registration -> modelMapper.map(registration, RegistrationDTO.class))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Delleting a registration")
    @ApiResponses( value ={
            @ApiResponse(code = 201, message = "Delleting with success"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByRegistrationId(@PathVariable Integer id) {
        Registration registration = registrationService.getRegistrationById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        registrationService.delete(registration);
    }


    @ApiOperation(value = "Updatting a registration")
    @ApiResponses( value ={
            @ApiResponse(code = 201, message = "Updatting with success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @PutMapping("{id}")
    public RegistrationDTO update(@PathVariable Integer id, @RequestBody ReqRegistrationDTO reqRegistrationDTO) {
        return registrationService.getRegistrationById(id).map(registration -> {
           registration.setName(reqRegistrationDTO.getName());
           registration.setDateOfRegistration(reqRegistrationDTO.getDateOfRegistration());
           registration = registrationService.update(registration);

           return modelMapper.map(registration, RegistrationDTO.class);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @ApiOperation(value = "Getting all registrations by order")
    @ApiResponses( value ={
            @ApiResponse(code = 201, message = "Getting all registrations with success"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @GetMapping
    public Page<RegistrationDTO> find(RegistrationDTO dto, Pageable pageRequest) {
        Registration filter = modelMapper.map(dto, Registration.class);
        Page<Registration> result = registrationService.find(filter, pageRequest);

        List<RegistrationDTO> list = result.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, RegistrationDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<RegistrationDTO>(list, pageRequest, result.getTotalElements());
    }


}
