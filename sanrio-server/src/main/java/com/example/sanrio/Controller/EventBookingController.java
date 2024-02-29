package com.example.sanrio.Controller;
import com.example.sanrio.Entity.EventBooking;
import com.example.sanrio.Pojo.EventBookingPojo;
import com.example.sanrio.Service.EventBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;

    @PostMapping(value="/save")
    public String saveEventBooking(@Valid @RequestBody EventBookingPojo eventBookingPojo){
        eventBookingService.saveEvent(eventBookingPojo);
        return "Saved";
    }

    @GetMapping("/getAll")
    public List<EventBooking> getALl() {
        return eventBookingService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<EventBooking> getById(@PathVariable("id") Long id) {
        return this.eventBookingService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        this.eventBookingService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public  String update(@PathVariable("id") Long id){
        return this.eventBookingService.update(Long.valueOf(id), new EventBookingPojo());
    }
}


