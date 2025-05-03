package com.project.VehicleRentalSystem.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;


    public Admin() {}

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
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
        return "Admin{" +
                "adminId=" + adminId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
