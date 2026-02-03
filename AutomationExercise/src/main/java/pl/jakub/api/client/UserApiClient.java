package pl.jakub.api.client;

import io.restassured.response.Response;
import org.springframework.stereotype.Component;
import pl.jakub.common.RegistrationData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
public class UserApiClient {

    private final ApiClient api;

    public UserApiClient(ApiClient api) {
        this.api = api;
    }

    public Response createUser(RegistrationData data) {
        LocalDate dob = data.dateOfBirth();

        return api.request()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("name", data.firstName() + " " + data.lastName())
                .formParam("email", data.email())
                .formParam("password", data.password())
                .formParam("title", toApiTitle(data.title()))
                .formParam("birth_date", String.valueOf(dob.getDayOfMonth()))
                .formParam("birth_month", String.valueOf(dob.getMonthValue()))
                .formParam("birth_year", String.valueOf(dob.getYear()))
                .formParam("firstname", data.firstName())
                .formParam("lastname", data.lastName())
                .formParam("company", data.company())
                .formParam("address1", data.address())
                .formParam("address2", data.addressSecond())
                .formParam("country", data.country().uiText())
                .formParam("zipcode", data.zipCode())
                .formParam("state", data.state())
                .formParam("city", data.city())
                .formParam("mobile_number", data.mobileNumber())
                .post("/createAccount");

    }

    private String toApiTitle(pl.jakub.common.Title title) {
        return switch (title) {
            case MR -> "Mr";
            case MRS -> "Mrs";
        };
    }
}
