package com.example.sanrio.Service.Impl;
import com.example.sanrio.Entity.*;
import com.example.sanrio.Pojo.EventPojo;
import com.example.sanrio.Repo.EventRepo;
import com.example.sanrio.Repo.UserRepo;
import com.example.sanrio.Service.EventService;
import com.example.sanrio.util.ImageToBase64;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final String UPLOAD_DIRECTORY = new StringBuilder().append(System.getProperty("user.dir")).append("/Feast-Images/event-images").toString();
    ImageToBase64 imageToBase64 = new ImageToBase64();
    private final EventRepo eventRepo;
    private final UserRepo userRepo;


    @Override
    public void saveEvent(EventPojo eventPojo) throws IOException {
        Event event=new Event();


        if(eventPojo.getId()!=null){
            event=eventRepo.findById(eventPojo.getId()).get();
        }

        event.setEventName(eventPojo.getEventName());
        event.setEventPrice(eventPojo.getEventPrice());
        event.setEventImage(event.getEventImage());
        event.setEventDescription(eventPojo.getEventDescription());

//        if (eventPojo.getEventImage() != null) {
//            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, eventPojo.getEventImage().getOriginalFilename());
//            Files.write(fileNameAndPath, eventPojo.getEventImage().getBytes());
//        }
//        event.setEventImage(eventPojo.getEventImage().getOriginalFilename());


        eventRepo.save(event); // insert query
        System.out.println("Saved succesfully");
    }


    public List<Event> findAll(){
        List<Event> events = eventRepo.findAll();
        events = events.stream().map(event -> {
            event.setEventImage(imageToBase64.getImageBase64("/event-images/" + event.getEventImage()));
            return event;
        }).collect(Collectors.toList());
        return events;
    }
    @Override
    public List<Event> getAll() {
        return this.eventRepo.findAll();
    }

    @Override
    public Optional<Event> getById(Long id) {
//        return Optional.empty();
        return eventRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        eventRepo.deleteById(Long.valueOf(id));
    }

    @Override
    public String update(Long id, EventPojo eventPojo) {
        return null;
    }
}





