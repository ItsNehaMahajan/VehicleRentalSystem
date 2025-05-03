package com.project.VehicleRentalSystem.Model;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private VehicleOwner owner;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "price_per_day")
    private double pricePerDay;

    @Column(name = "location")
    private String location;

    @Column(name = "availability")
    private Boolean availability = true;


    public Vehicle() {}


    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleOwner getOwner() {
        return owner;
    }

    public void setOwner(VehicleOwner owner) {
        this.owner = owner;
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

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", owner=" + owner +
                ", vehicleType='" + vehicleType + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", location='" + location + '\'' +
                ", availability=" + availability +
                '}';
    }



}
