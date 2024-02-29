package com.example.sanrio.Service.Impl;

import com.example.sanrio.Entity.Event;
import com.example.sanrio.Entity.EventBooking;
import com.example.sanrio.Entity.User;
import com.example.sanrio.Pojo.EventBookingPojo;
import com.example.sanrio.Repo.EventBookingRepo;
import com.example.sanrio.Repo.EventRepo;
import com.example.sanrio.Repo.UserRepo;
import com.example.sanrio.Service.EventBookingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventBookingServiceImpl implements EventBookingService {

    private final EventBookingRepo eventBookingRepo;
    private final EventRepo eventRepo;
    private final UserRepo userRepo;

    @Override
    public void saveEvent(EventBookingPojo eventBookingPojo) {
        EventBooking eventBooking = new EventBooking();

        eventBooking.setEventDate(eventBookingPojo.getEventDate());
        eventBooking.setEventTime(eventBookingPojo.getEventTime());
        eventBooking.setNoOfGuest(eventBookingPojo.getNoOfGuest());
        eventBooking.setSpecialRequest(eventBookingPojo.getSpecialRequest());
        eventBooking.setEventStatus(eventBookingPojo.isEventStatus());

        User user = userRepo.findById(eventBookingPojo.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with Id: " + eventBookingPojo.getUserId()));
        eventBooking.setUser(user);

        Event event = eventRepo.findById(eventBookingPojo.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found with Id: " + eventBookingPojo.getEventId()));
        eventBooking.setEvent(event);

        // Missing save operation
        eventBookingRepo.save(eventBooking);
    }

    @Override
    public List<EventBooking> getAll() {
        return this.eventBookingRepo.findAll();
    }

    @Override
    public Optional<EventBooking> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        eventBookingRepo.deleteById(Long.valueOf(id));
    }
    @Override
    public String update(Long id, EventBookingPojo eventBookingPojo) {
        EventBooking existingEvent = eventBookingRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("EventBooking bot found with Id : " + id));

        existingEvent.setEventDate(eventBookingPojo.getEventDate());
        existingEvent.setEventTime(eventBookingPojo.getEventTime());
        existingEvent.setEventStatus(eventBookingPojo.isEventStatus());
        existingEvent.setNoOfGuest(eventBookingPojo.getNoOfGuest());
        existingEvent.setSpecialRequest(eventBookingPojo.getSpecialRequest());


                eventBookingRepo.save(existingEvent);
        return "Updated Successfully";
    }
}
