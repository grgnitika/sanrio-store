package com.example.sanrio.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Table(name = "eventbooking")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class EventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name="event_date", nullable=false)
    private Date eventDate;

    @Column(name="event_time", nullable = false)
    private LocalTime eventTime;

    @Column(name="no_of_Guest" , nullable = false)
    private Integer noOfGuest;

    @Column(name="special_request")
    private String specialRequest;

    @Column(name = "event_status")
    private boolean eventStatus;


//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "event_user",
//            joinColumns = @JoinColumn(name = "event_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private Set<User> users;
}
