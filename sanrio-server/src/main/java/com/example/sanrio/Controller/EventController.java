package com.example.sanrio.Controller;
import com.example.sanrio.Entity.Event;
import com.example.sanrio.Pojo.EventPojo;
import com.example.sanrio.Service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @PostMapping(value="/save")
    public String saveEvent(@Valid @RequestBody EventPojo eventPojo) throws IOException {
        eventService.saveEvent(eventPojo);
        return "Saved";
    }

    @GetMapping("/getAll")
    public List<Event> getAll() {
        return eventService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<Event> getById(@PathVariable("id") Long id) {
        return this.eventService.getById(id);
    }
    @PutMapping("/update/{id}")
    public  String update(@PathVariable("id") Integer id){
        return this.eventService.update(Long.valueOf(id), new EventPojo());
    }
}


