package com.example.sanrio.Repo;

import com.example.sanrio.Entity.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventBookingRepo extends JpaRepository<EventBooking , Long> {
}
