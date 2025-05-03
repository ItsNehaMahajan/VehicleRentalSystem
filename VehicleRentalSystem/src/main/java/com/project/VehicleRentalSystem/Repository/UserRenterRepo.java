package com.project.VehicleRentalSystem.Repository;

import com.project.VehicleRentalSystem.Model.UserRenter;
import com.project.VehicleRentalSystem.Model.Vehicle;
import com.project.VehicleRentalSystem.Model.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRenterRepo extends JpaRepository<UserRenter,Long> {


    UserRenter findByEmail(String email);


}





