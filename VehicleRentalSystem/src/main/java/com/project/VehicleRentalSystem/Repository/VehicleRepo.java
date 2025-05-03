package com.project.VehicleRentalSystem.Repository;

import com.project.VehicleRentalSystem.Model.Vehicle;
import com.project.VehicleRentalSystem.Model.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByOwner(VehicleOwner owner);
    
    Vehicle findByBrandAndModelAndOwner(String brand, String model, VehicleOwner loggedInOwner);
    

}

