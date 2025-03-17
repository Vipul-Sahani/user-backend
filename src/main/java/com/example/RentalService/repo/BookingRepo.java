package com.example.RentalService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RentalService.model.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

    List<Booking> findByUserId(int userId);

}
