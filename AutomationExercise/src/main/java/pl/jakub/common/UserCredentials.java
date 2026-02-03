package pl.jakub.common;

import java.time.LocalDate;

public record UserCredentials(
        String name,
        String email,
        String password,
        Title title,
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
) {
    public RegistrationData toRegistrationData() {
        return new RegistrationData(
                title,
                email,
                password,
                dateOfBirth,
                firstName,
                lastName,
                company,
                address,
                addressSecond,
                country,
                state,
                city,
                zipCode,
                mobileNumber
        );
    }
}
