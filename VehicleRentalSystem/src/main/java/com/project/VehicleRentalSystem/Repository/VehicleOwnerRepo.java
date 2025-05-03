package com.project.VehicleRentalSystem.Repository;

import com.project.VehicleRentalSystem.Model.Vehicle;
import com.project.VehicleRentalSystem.Model.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleOwnerRepo extends JpaRepository<VehicleOwner, Long> {

    VehicleOwner findByEmail(String email);
}
