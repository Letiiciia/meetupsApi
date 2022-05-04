package com.womakerscode.microservicemeetup.controller;

import com.womakerscode.microservicemeetup.controller.dto.RequisitionMeetupDTO;
import com.womakerscode.microservicemeetup.controller.dto.ResponseMeetupDTO;
import com.womakerscode.microservicemeetup.model.entity.Meetup;
import com.womakerscode.microservicemeetup.model.entity.Registration;
import com.womakerscode.microservicemeetup.service.MeetupService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/meetup")
public class MeetupController {

    @Autowired
    private MeetupService meetupService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Creating a new meetup")
    @ApiResponses( value ={
            @ApiResponse(code = 201, message = "Meetup created with success"),
            @ApiResponse(code = 400, message = "There is any information invalid in the body"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @PostMapping
    public ResponseEntity createMeetup(@RequestBody RequisitionMeetupDTO reqMeetupDTO) {
        String message = null;
        try {
        Meetup meetup = modelMapper.map(reqMeetupDTO, Meetup.class);
        meetup = this.meetupService.createMeetup(meetup);
        ResponseMeetupDTO body = modelMapper.map(meetup, ResponseMeetupDTO.class);
          message = "Created meetup " +  reqMeetupDTO.getMeetupName();
        }catch (Exception e) {
            e.printStackTrace();
            message = "Internal Trouble to create";
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @ApiOperation(value = "Listing all Events")
    @ApiResponses( value ={
            @ApiResponse(code = 200, message = "Listing meetus with success"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @GetMapping
    public ResponseEntity<List<ResponseMeetupDTO>> listAllMeetups() {
        List<ResponseMeetupDTO> meetups = this.meetupService.listAllEvents();

        return ResponseEntity.status(HttpStatus.OK).body(meetups);
    };

    @ApiOperation(value = "Updating an Event")
    @ApiResponses( value ={
            @ApiResponse(code = 200, message = "Updating a meetu with success"),
            @ApiResponse(code = 400, message = "There is any information invalid in the body"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @PutMapping("{id}")
    public ResponseEntity<Object> updateMeetup(@RequestBody RequisitionMeetupDTO requisitionMeetupDTO, @PathVariable Integer id) {
        Object response = this.meetupService.updateMeetup(requisitionMeetupDTO, id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Delleting a registration")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Delleting with success"),
            @ApiResponse(code = 500, message = "It had an internal trouble")

    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByMeetupId(@PathVariable Integer id) {
        Meetup meetup = this.meetupService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.meetupService.delete(meetup);
    }


}
