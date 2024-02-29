package com.example.sanrio.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @SequenceGenerator(name = "event_seq_gen", sequenceName = "event_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "event_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "event_name")
    private String eventName;

    @Column (name = "event_image")
    private String eventImage;

    @Column(name="event_description")
    private String eventDescription;

    @Column(name="event_price", nullable=false)
    private Integer eventPrice;

}