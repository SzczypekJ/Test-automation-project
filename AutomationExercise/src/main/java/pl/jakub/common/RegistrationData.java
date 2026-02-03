package pl.jakub.common;

import java.time.LocalDate;

public record RegistrationData(
        Title title,
        String email,
        String password,
        LocalDate dateOfBirth,
        String firstName,
        String lastName,
        String company,
        String address,
        String addressSecond,
        Country country,
        String state,
        String city,
        String zipCode,
        String mobileNumber
) {}
