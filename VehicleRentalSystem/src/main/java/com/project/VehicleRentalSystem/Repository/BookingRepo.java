package com.project.VehicleRentalSystem.Repository;

import com.project.VehicleRentalSystem.Model.Booking;

import com.project.VehicleRentalSystem.Model.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    List<Booking> findByOwnerName(String ownerName);



}
