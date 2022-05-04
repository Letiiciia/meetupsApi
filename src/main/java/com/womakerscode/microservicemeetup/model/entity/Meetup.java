package com.womakerscode.microservicemeetup.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "meetup")
public class Meetup {
    @Id
    @Column(name="meetup_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="meetup_name")
    private String meetupName;

    @Column(name="meetup_date")
    private LocalDate meetupDate;

    private boolean registrated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Registration> registration;

}
