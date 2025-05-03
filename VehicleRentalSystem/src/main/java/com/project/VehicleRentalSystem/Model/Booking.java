package com.project.VehicleRentalSystem.Model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "price_per_day")
    private Double pricePerDay;

    @Column(name = "location")
    private String location;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "status")
    private String status;

    public Booking() {
    }

    public Booking(Long id, String ownerName, String vehicleType, String brand, String model, Double pricePerDay, String location, LocalDate bookingDate, String status) {
        this.id = id;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.location = location;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", location='" + location + '\'' +
                ", bookingDate=" + bookingDate +
                ", status='" + status + '\'' +
                '}';
    }
}
