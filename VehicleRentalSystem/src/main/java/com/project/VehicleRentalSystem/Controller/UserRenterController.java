package com.project.VehicleRentalSystem.Controller;

import com.project.VehicleRentalSystem.Model.Booking;
import com.project.VehicleRentalSystem.Model.UserRenter;
import com.project.VehicleRentalSystem.Model.Vehicle;
import com.project.VehicleRentalSystem.Repository.BookingRepo;
import com.project.VehicleRentalSystem.Repository.UserRenterRepo;
import com.project.VehicleRentalSystem.Repository.VehicleRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class UserRenterController {


    private UserRenterRepo userRenterRepo;
    private VehicleRepo vehicleRepo;
    private BookingRepo bookingRepo;


    @Autowired
    public UserRenterController(UserRenterRepo userRenterRepo, VehicleRepo vehicleRepo, BookingRepo bookingRepo) {
        this.userRenterRepo = userRenterRepo;
        this.vehicleRepo = vehicleRepo;
        this.bookingRepo = bookingRepo;
    }


    @GetMapping("/loginforUser")
    public String loginforUser(Model model) {
        model.addAttribute("user", new UserRenter());
        return "loginforUser";
    }


    @PostMapping("/registerUser")
    public String registerUserSubmit(@ModelAttribute("user") UserRenter userRenter) {
        userRenterRepo.save(userRenter);
        return "loginforUser";
    }


    @PostMapping("/afterUserLoginSubmit")
    public String loginCheck(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpSession session,
                             Model model) {
        UserRenter user = userRenterRepo.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            session.setAttribute("loggedInUser", user);
            model.addAttribute("username", user.getName());
            model.addAttribute("dob", user.getDob());
            model.addAttribute("contact", user.getContact());
            model.addAttribute("address", user.getAddress());
            model.addAttribute("email", user.getEmail());
            return "User";
        } else {
            model.addAttribute("errorMessage", "Invalid credentials");
            return "loginforUser";
        }
    }

    @GetMapping("/User")
    public String getUserProfile(HttpSession session, Model model) {
        UserRenter user = (UserRenter) session.getAttribute("loggedInUser");

        if (user != null) {

            session.setAttribute("loggedInUser", user);
            model.addAttribute("username", user.getName());
            model.addAttribute("dob", user.getDob());
            model.addAttribute("contact", user.getContact());
            model.addAttribute("address", user.getAddress());
            model.addAttribute("email", user.getEmail());
        }
        return "User";
    }


    @GetMapping("/browsevehicle")
    public String manageVehicles(Model model,HttpSession session) {

        UserRenter user = (UserRenter) session.getAttribute("loggedInUser");
        session.setAttribute("loggedInUser", user);
        model.addAttribute("username", user.getName());

        List<Vehicle> vehicleList = vehicleRepo.findAll();
        model.addAttribute("vehicles", vehicleList);
        return "browsevehicle";
    }

    @GetMapping("/bookinghistory")
    public String bookinghistory(Model model,HttpSession session) {

        UserRenter user = (UserRenter) session.getAttribute("loggedInUser");
        session.setAttribute("loggedInUser", user);
        model.addAttribute("username", user.getName());

        List<Booking> bookingList = bookingRepo.findAll();
        model.addAttribute("booking", bookingList);
        return "bookinghistory";
    }

    @PostMapping("/book")
    public String bookVehicle(@RequestParam String ownerName,
                              @RequestParam String vehicleType,
                              @RequestParam String brand,
                              @RequestParam String model,
                              @RequestParam Double pricePerDay,
                              @RequestParam String location,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate,
                              Model model1,@ModelAttribute("book") Booking booking) {


        booking.setOwnerName(ownerName);
        booking.setVehicleType(vehicleType);
        booking.setBrand(brand);
        booking.setModel(model);
        booking.setPricePerDay(pricePerDay);
        booking.setLocation(location);
        booking.setBookingDate(bookingDate);
        booking.setStatus("Confirmed");

        bookingRepo.save(booking);

        List<Booking> bookingList = bookingRepo.findAll();
        model1.addAttribute("booking", bookingList);

        return "bookinghistory";
    }

    @GetMapping("/payments")
    public String payments() {

        return "payments";
    }


}
