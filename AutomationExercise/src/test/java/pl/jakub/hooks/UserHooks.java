package pl.jakub.hooks;

import io.cucumber.java.Before;
import io.restassured.response.Response;
import pl.jakub.api.assertions.UserApiClientAssertions;
import pl.jakub.api.client.UserApiClient;
import pl.jakub.common.TestUserProvider;
import pl.jakub.context.CommonContext;

public class UserHooks {

    private final TestUserProvider testUserProvider;
    private final UserApiClient userApiClient;
    private final CommonContext commonContext;
    private final UserApiClientAssertions userApiClientAssertions;

    public UserHooks(TestUserProvider testUserProvider, UserApiClient userApiClient, CommonContext commonContext, UserApiClientAssertions userApiClientAssertions) {
        this.testUserProvider = testUserProvider;
        this.userApiClient = userApiClient;
        this.commonContext = commonContext;
        this.userApiClientAssertions = userApiClientAssertions;
    }

    @Before("@requiresApiUser")
    public void createUserViaApi() {
        var registration = testUserProvider.newUniqueRegistrationData();
        Response response = userApiClient.createUser(registration);
        userApiClientAssertions.assertUserCreated(response);

        var creds = testUserProvider.toCredentials(registration);
        commonContext.setUser(creds);
    }
}
