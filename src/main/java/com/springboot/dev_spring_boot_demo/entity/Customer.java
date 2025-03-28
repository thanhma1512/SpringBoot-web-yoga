package com.springboot.dev_spring_boot_demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Company name is mandatory")
    @Size(min = 2, max = 50, message = "Company name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Company name must contain only letters")
    private String companyName;

    @NotBlank(message = "Street address is mandatory")
    @Size(min = 2, max = 100, message = "Street address must be between 2 and 100 characters")
    private String streetAddress;

    @NotBlank(message = "City is mandatory")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "City must contain only letters")
    private String city;

    @NotBlank(message = "Region is mandatory")
    @Size(min = 2, max = 50, message = "Region must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Region must contain only letters")
    private String region;

    @NotBlank(message = "Postal code is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]{5}$", message = "Postal code must be exactly 5 alphanumeric characters")
    private String postalCode;

    @NotBlank(message = "Country is mandatory")
    @Size(min = 2, max = 50, message = "Country must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Country must contain only letters")
    private String country;

    public Customer() {
    }

    public Customer(String companyName, String streetAddress, String city, String region, String postalCode, String country) {
        this.companyName = companyName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
