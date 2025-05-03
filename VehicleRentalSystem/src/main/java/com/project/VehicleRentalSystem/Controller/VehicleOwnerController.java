package com.project.VehicleRentalSystem.Controller;

import com.project.VehicleRentalSystem.Model.Booking;
import com.project.VehicleRentalSystem.Model.Vehicle;
import com.project.VehicleRentalSystem.Model.VehicleOwner;
import com.project.VehicleRentalSystem.Repository.BookingRepo;
import com.project.VehicleRentalSystem.Repository.VehicleOwnerRepo;
import com.project.VehicleRentalSystem.Repository.VehicleRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleOwnerController {

    private VehicleOwnerRepo vehicleOwnerRepo;
    private VehicleRepo vehicleRepo;
    private BookingRepo bookingRepo;

    @Autowired
    public VehicleOwnerController(VehicleOwnerRepo vehicleOwnerRepo, VehicleRepo vehicleRepo, BookingRepo bookingRepo) {
        this.vehicleOwnerRepo = vehicleOwnerRepo;
        this.vehicleRepo = vehicleRepo;
        this.bookingRepo = bookingRepo;
    }


    @GetMapping("/loginforOwner")
    public String loginforOwner(Model model) {
        model.addAttribute("owner", new VehicleOwner());
        return "loginforOwner";
    }

    @PostMapping("/registerOwner")
    public String registerOwnerSubmit(@ModelAttribute("owner") VehicleOwner vehicleOwner) {
        vehicleOwnerRepo.save(vehicleOwner);
        return "loginforOwner";
    }


    @PostMapping("/afterOwnerLoginSubmit")
    public String loginCheck(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model, HttpSession session) {

        VehicleOwner user = vehicleOwnerRepo.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInOwner", user);

            session.setAttribute("loggedInUser", user);
            model.addAttribute("username", user.getName());

            List<Vehicle> vehicles = vehicleRepo.findByOwner(user);
            model.addAttribute("vehicles", vehicles);
            return "Owner";
        } else {

            model.addAttribute("errorMessage", "Invalid credentials");
            return "loginforOwner";
        }
    }

    @GetMapping("/Owner")
    public String showOwnerDashboard(Model model, HttpSession session) {

        VehicleOwner loggedInOwner = (VehicleOwner) session.getAttribute("loggedInOwner");
        model.addAttribute("loggedInOwner", loggedInOwner);

        model.addAttribute("username", loggedInOwner.getName());

        List<Vehicle> vehicles = vehicleRepo.findByOwner(loggedInOwner);
        model.addAttribute("vehicles", vehicles);
        return "Owner";
    }

    @GetMapping("/AddnewVehicle")
    public String showAddVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "AddnewVehicle";
    }

    @PostMapping("/AddnewVehicle")
    public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle, HttpSession session,Model model) {
        VehicleOwner loggedInOwner = (VehicleOwner) session.getAttribute("loggedInOwner");
        vehicle.setOwner(loggedInOwner);

        vehicleRepo.save(vehicle);

        model.addAttribute("loggedInOwner", loggedInOwner);

        List<Vehicle> vehicles = vehicleRepo.findByOwner(loggedInOwner);
        model.addAttribute("vehicles", vehicles);
        return "Owner";
    }


    @GetMapping("/DeletenewVehicle")
    public String showDeleteVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "DeletenewVehicle";
    }

    @PostMapping("/DeletenewVehicle")
    public String deleteVehicle(@ModelAttribute("vehicle") Vehicle vehicle,
                                HttpSession session,
                                Model model) {
        VehicleOwner loggedInOwner = (VehicleOwner) session.getAttribute("loggedInOwner");

        if (loggedInOwner != null) {
            Vehicle vehicleToDelete = vehicleRepo.findByBrandAndModelAndOwner(
                    vehicle.getBrand(),
                    vehicle.getModel(),
                    loggedInOwner
            );

            if (vehicleToDelete != null) {
                vehicleRepo.delete(vehicleToDelete);
                model.addAttribute("successMessage", "Vehicle deleted successfully.");
            } else {
                model.addAttribute("errorMessage", "Vehicle not found or you do not have permission to delete it.");
            }

            List<Vehicle> vehicles = vehicleRepo.findByOwner(loggedInOwner);
            model.addAttribute("vehicles", vehicles);
            model.addAttribute("loggedInOwner", loggedInOwner);
            return "Owner";
        }

        List<Vehicle> vehicles = vehicleRepo.findByOwner(loggedInOwner);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("loggedInOwner", loggedInOwner);
        return "Owner";
    }

    @GetMapping("/MyBookings")
    public String showOwnerBookings(Model model, HttpSession session) {
        VehicleOwner loggedInOwner = (VehicleOwner) session.getAttribute("loggedInOwner");

        if (loggedInOwner != null) {
            String ownerName = loggedInOwner.getName();
            List<Booking> bookingList = bookingRepo.findByOwnerName(ownerName);
            model.addAttribute("booking", bookingList);
            return "MyBookings";
        }

        return "loginforOwner";
    }

}
