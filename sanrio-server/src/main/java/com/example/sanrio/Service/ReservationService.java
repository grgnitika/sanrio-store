package com.example.sanrio.Service;


import com.example.sanrio.Entity.Reservation;
import com.example.sanrio.Pojo.ReservationPojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReservationService {
    Reservation saveReservation(ReservationPojo reservationPojo);


    List<Reservation> findAll();

    Optional<Reservation> findById(Integer id);

    Integer deleteById(Integer id);

}

