package pl.jakub.api.assertions;

import io.restassured.response.Response;

public interface UserApiClientAssertions {

    /**
     * Verifies that the new user was created correctly via API call
     *
     @param response response returned by API call
     */
     void assertUserCreated(Response response);
}
