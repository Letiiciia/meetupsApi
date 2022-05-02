package com.womakerscode.microservicemeetup.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@ToString(exclude = "meetup")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @Column(name="registration_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="person_name")
    private String name;

    @Column(name="date_of_registration")
    private LocalDate dateOfRegistration;

    @Column
    private String registration;

    @JoinColumn(name = "id")
    @ManyToOne
    @JsonIgnore
    private Meetup meetup;


}
