package com.example.sanrio.Repo;


import com.example.sanrio.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Integer> {

//    List<Reservation> findByDateAndStatus(String date, String status);
}
