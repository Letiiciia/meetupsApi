package com.womakerscode.microservicemeetup.service;

import com.womakerscode.microservicemeetup.controller.dto.RequisitionRegistrationDTO;
import com.womakerscode.microservicemeetup.exception.BusinessException;
import com.womakerscode.microservicemeetup.model.entity.Meetup;
import com.womakerscode.microservicemeetup.model.entity.Registration;
import com.womakerscode.microservicemeetup.repository.RegistrationRepository;
import com.womakerscode.microservicemeetup.service.impl.RegistrationServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RegistrationServiceTest {

    RegistrationService registrationService;

    @MockBean
    RegistrationRepository repository;

    @MockBean
    MeetupService service;

    @BeforeEach
    public void setUp() {
        this.registrationService = new RegistrationServiceImpl(repository, service);


    }


    @Test
    @DisplayName("Should get an Registration by id")
    public void getRegistrationIdTest() {

        //cenario
        Integer id = 11;
        Registration registration = createValidRegistration();
        registration.setId(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(registration));

        //execução
        Optional<Registration> foundRegistration = registrationService.getRegistrationById(registration.getId());

        //assert
        assertThat(foundRegistration.isPresent()).isTrue();
        assertThat(foundRegistration.get().getId()).isEqualTo(id);
        assertThat(foundRegistration.get().getName()).isEqualTo(registration.getName());
        assertThat(foundRegistration.get().getDateOfRegistration()).isEqualTo(registration.getDateOfRegistration());
        assertThat(foundRegistration.get().getNickName()).isEqualTo(registration.getNickName());

    }

    @Test
    @DisplayName("Should return empty when get an registration by id wich it doesn't exist")
    public void registrationNotFoundByIdTest() {

        //cenario
        Integer id = 11;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());


        //execução
        Optional<Registration> registration = registrationService.getRegistrationById(id);

        //assert
        assertThat(registration.isPresent()).isFalse();
    }


    @Test
    @DisplayName("Should update an registration")
    public void updateRegistration() {
        //cenario
        Integer id = 11;
        Registration updatingRegistration = Registration.builder().id(11).build();

        //execução
        Registration updatedRegistration = createValidRegistration();
        updatedRegistration.setId(id);

        Mockito.when(repository.save(updatingRegistration)).thenReturn(updatedRegistration);
        Registration registration = registrationService.update(updatingRegistration);

        //assert
        assertThat(registration.getId()).isEqualTo(updatedRegistration.getId());
        assertThat(registration.getName()).isEqualTo(updatedRegistration.getName());
        assertThat(registration.getDateOfRegistration()).isEqualTo(updatedRegistration.getDateOfRegistration());
        assertThat(registration.getNickName()).isEqualTo(updatedRegistration.getNickName());

    }

    @Test
    @DisplayName("Should filter registration must by properties")
    public void findRegistrationTest() {
        //cenario
        Registration registration = createValidRegistration();
        PageRequest pageRequest = PageRequest.of(0,10);
        List<Registration> listRegistrations = Arrays.asList(registration);
        Page<Registration> page = new PageImpl<Registration>(Arrays.asList(registration),
                PageRequest.of(0,10), 1);

        //execução
        Mockito.when(repository.findAll(Mockito.any(Example.class), Mockito.any(PageRequest.class)))
                .thenReturn(page);

        Page<Registration> result = registrationService.find(registration, pageRequest);

        //assert
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo(listRegistrations);
        assertThat(result.getPageable().getPageNumber()).isEqualTo(0);


        assertThat(result.getPageable().getPageSize()).isEqualTo(10);


    }

    @Test
    @DisplayName("Should get an Registration model by registration attribute")
    public void getRegistrationByRegistrationAttribute() {

        String registrationAttribute = "1234";

        Mockito.when(repository.findByNickName(registrationAttribute))
                .thenReturn(Optional.of(Registration.builder().id(11).nickName(registrationAttribute).build()));

            Optional<Registration> registration = registrationService.getRegistrationByRegistrationAttribute(registrationAttribute);

            assertThat(registration.isPresent()).isTrue();
        assertThat(registration.get().getId()).isEqualTo(11);
        assertThat(registration.get().getNickName()).isEqualTo(registrationAttribute);

        Mockito.verify(repository, Mockito.times(1)).findByNickName(registrationAttribute);
        
    }


    private Registration createValidRegistration() {
        return Registration.builder()
                .id(101)
                .name("Ana Neri")
                .dateOfRegistration(LocalDate.now())
                .nickName("001")
                .build();

    }

    private Meetup createNewMeetup() {
        return Meetup.builder().meetupName("Spring boot Bootcamp").meetupDate(LocalDate.now()).registrated(true).build();
    }

    private Registration createNewRegistration() {
        return Registration.builder().name("Ana Neri").dateOfRegistration(LocalDate.now()).nickName("001").meetup(createNewMeetup()).build();
    }

}
