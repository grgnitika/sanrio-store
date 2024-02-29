package com.example.sanrio.Service;

import com.example.sanrio.Entity.EventBooking;
import com.example.sanrio.Pojo.EventBookingPojo;

import java.util.List;
import java.util.Optional;

public interface EventBookingService {
    void saveEvent(EventBookingPojo eventBookingPojo);
    List<EventBooking> getAll();

    Optional<EventBooking> getById(Long id);

    void deleteById(Long id);

    String update(Long id, EventBookingPojo eventBookingPojo);
}
