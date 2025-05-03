package com.project.VehicleRentalSystem.Controller;

import com.project.VehicleRentalSystem.Model.UserRenter;
import com.project.VehicleRentalSystem.Model.Vehicle;
import com.project.VehicleRentalSystem.Model.VehicleOwner;
import com.project.VehicleRentalSystem.Repository.UserRenterRepo;
import com.project.VehicleRentalSystem.Repository.VehicleOwnerRepo;
import com.project.VehicleRentalSystem.Repository.VehicleRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class AdminController {

    UserRenterRepo userRenterRepo;
     VehicleRepo vehicleRepo;
     VehicleOwnerRepo vehicleOwnerRepo;


    @Autowired
    public AdminController(UserRenterRepo userRenterRepo, VehicleRepo vehicleRepo,VehicleOwnerRepo vehicleOwnerRepo) {
        this.userRenterRepo = userRenterRepo;
        this.vehicleRepo = vehicleRepo;
        this.vehicleOwnerRepo = vehicleOwnerRepo;
    }

    @GetMapping("/Home")
    public String Home() {
        return "Home";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/Admin")
    public String showAdminDashboard(Model model) {

        List<Vehicle> vehicleList = vehicleRepo.findAll();
        model.addAttribute("vehicles", vehicleList);
        return "Admin";
    }

        @GetMapping("/approve")
        public String approveVehicle(Model model ) {
            List<Vehicle> vehicleList = vehicleRepo.findAll();
            model.addAttribute("vehicles", vehicleList);
            return "Admin";
        }

        @GetMapping("/reject/{id}")
        public String rejectVehicle(@PathVariable Long id,Model model) {
            vehicleRepo.deleteById(id);
            List<Vehicle> vehicleList = vehicleRepo.findAll();
            model.addAttribute("vehicles", vehicleList);
            return "Admin";
        }

        @PostMapping("/afterAdminLoginSubmit")
        public String afterAdminLoginSubmit(@RequestParam("email") String email,
                                            @RequestParam("password") String password,
                                            Model model) {
            if ("admin@gmail.com".equals(email) && "admin".equals(password)) {

                List<Vehicle> vehicleList = vehicleRepo.findAll();
                model.addAttribute("vehicles", vehicleList);
                return "Admin";
            } else {
                model.addAttribute("errorMessage", "Invalid credentials");
                return "login";
            }
        }

        @GetMapping("/manageusers")
        public String manageUsers(Model model) {
            List<UserRenter> users = userRenterRepo.findAll();
            model.addAttribute("users", users);
            return "manageusers";
        }

        @GetMapping("/manageowner")
        public String showAllVehicleOwners(Model model) {
            List<VehicleOwner> owners = vehicleOwnerRepo.findAll();
            model.addAttribute("owners", owners);
            return "manageowner";
        }

}



