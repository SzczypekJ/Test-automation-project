package pl.jakub.api.assertions.impl;

import io.restassured.response.Response;
import org.springframework.stereotype.Component;
import pl.jakub.api.assertions.UserApiClientAssertions;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
public class UserApiClientAssertionsImpl  implements UserApiClientAssertions {
    @Override
    public void assertUserCreated(Response response) {
        int status = response.statusCode();
        assertTrue(status == 200 || status == 201,
                "Expected HTTP 200 or 201 for createAccount but was: " + status + "\nBody: " + response.asString());

        String body = response.asString();
        assertTrue(body.contains("User created!"), "Unexpected response: " + body);
    }
}
