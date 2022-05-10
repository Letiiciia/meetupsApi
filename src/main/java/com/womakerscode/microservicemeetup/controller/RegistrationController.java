package com.womakerscode.microservicemeetup.controller;

import com.womakerscode.microservicemeetup.controller.dto.ResponseRegistrationDTO;
import com.womakerscode.microservicemeetup.controller.dto.converter.RequisitionRegistrationDTOToRegistration;
import com.womakerscode.microservicemeetup.exception.BusinessException;
import com.womakerscode.microservicemeetup.model.entity.Meetup;
import com.womakerscode.microservicemeetup.model.entity.Registration;
import com.womakerscode.microservicemeetup.controller.dto.RequisitionRegistrationDTO;
import com.womakerscode.microservicemeetup.service.MeetupService;
import com.womakerscode.microservicemeetup.service.RegistrationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private MeetupService meetupService;
    private ModelMapper modelMapper;


    @ApiOperation(value = "Creating a new registration")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registration created with success"),
            @ApiResponse(code = 400, message = "There is any information invalid in the body"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestBody @Valid RequisitionRegistrationDTO requisitionRegistrationDTO) {
        if (this.meetupService.listAllEvents().isEmpty()) {
            throw new BusinessException("Don't have any event registrated, create one first");
        }
        Meetup meetup = this.meetupService.findById(requisitionRegistrationDTO.getMeetupId()).orElseThrow(() -> new BusinessException("Meetup doesn't exist"));

        String message = null;

        Registration registration = RequisitionRegistrationDTOToRegistration.convert(requisitionRegistrationDTO, meetup);
        String returnMessage = registrationService.save(registration);
        if (returnMessage.equals("Registration already created")) {
            message = "Registration already created";
        }else{

            message = "Created Registration of " + requisitionRegistrationDTO.getName() + " to meetup " + meetup.getMeetupName();
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @ApiOperation(value = "Getting a specific registration by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Getting an registration with success"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseRegistrationDTO get(@PathVariable Integer id) {

        return registrationService
                .getRegistrationById(id)
                .map(registration -> modelMapper.map(registration, ResponseRegistrationDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Delleting a registration")
    @ApiResponses(value = {
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
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Updatting with success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody RequisitionRegistrationDTO requisitionRegistrationDTO) {
        registrationService.getRegistrationById(id).map(registration -> {
            registration.setName(requisitionRegistrationDTO.getName());
            registration.setDateOfRegistration(requisitionRegistrationDTO.getDateOfRegistration());
            registration = registrationService.update(registration);

            return modelMapper.map(registration, ResponseRegistrationDTO.class);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        String message = "Updated";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @ApiOperation(value = "Getting all registrations by order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Getting all registrations with success"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @GetMapping("/pageable")
    public Page<ResponseRegistrationDTO> find(ResponseRegistrationDTO dto, Pageable pageRequest) {
        Registration filter = modelMapper.map(dto, Registration.class);
        Page<Registration> result = registrationService.find(filter, pageRequest);

        List<ResponseRegistrationDTO> list = result.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, ResponseRegistrationDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<ResponseRegistrationDTO>(list, pageRequest, result.getTotalElements());
    }

    @ApiOperation(value = "Getting all registrations")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Getting all registrations with success"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @GetMapping("/all")
    ResponseEntity<List<ResponseRegistrationDTO>> listAllRegistrations() {

        List<ResponseRegistrationDTO> list = this.registrationService.listAllRegistrations();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}
