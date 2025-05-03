package com.project.VehicleRentalSystem.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_renter")
public class UserRenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="name")
    private String name;

    @Column(name="dob")
    private LocalDate dob;

    @Column(name="contact")
    private String contact;

    @Column(name="address")
    private String address;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;


    public UserRenter() {}

    public UserRenter(String name, LocalDate dob, String contact, String address, String email, String password) {
        this.name = name;
        this.dob = dob;
        this.contact = contact;
        this.address = address;
        this.email = email;
        this.password = password;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRenter{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
