package com.example.sanrio.Service.Impl;

import com.example.sanrio.Entity.ManageTable;
import com.example.sanrio.Entity.Reservation;
import com.example.sanrio.Pojo.ReservationPojo;
import com.example.sanrio.Repo.ManageTableRepo;
import com.example.sanrio.Repo.ReservationRepo;
import com.example.sanrio.Service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepo reservationRepo;
    private final ManageTableRepo manageTableRepo;

    @Override
    @Transactional // Added transactional annotation for better database consistency
    public Reservation saveReservation(ReservationPojo reservationPojo) {
        Reservation reservation;

        if (reservationPojo.getId() != null) {
            reservation = reservationRepo.findById(reservationPojo.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Reservation not found with ID: " + reservationPojo.getId()));
        } else {
            reservation = new Reservation();
        }

        reservation.setName(reservationPojo.getName());
        reservation.setPhone(reservationPojo.getPhone());
        reservation.setPersons(reservationPojo.getPersons());
        reservation.setDay(reservationPojo.getDay());
        reservation.setId(reservationPojo.getId());

        ManageTable manageTable = manageTableRepo.findById(reservationPojo.getTableId())
                .orElseThrow(() -> new EntityNotFoundException("Table not found with ID: " + reservationPojo.getTableId()));

        if ("Booked".equals(manageTable.getStatus())) {
            throw new IllegalArgumentException("Table Not Available");
        }

        reservation.setTable(manageTable);
        reservationRepo.save(reservation);
        System.out.println("Table Reserved Successfully");
        return reservation;


    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    @Override
    public Integer deleteById(Integer id) {
        Integer tableId =reservationRepo.findById(id).get().getTable().getId();
        reservationRepo.deleteById(id);

        return tableId;
    }

    @Override
    public Optional<Reservation> findById(Integer id) {
        return reservationRepo.findById(id);
    }
}
