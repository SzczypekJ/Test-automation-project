package pl.jakub.hooks;

import io.cucumber.java.Before;
import org.springframework.stereotype.Component;
import pl.jakub.api.client.UserApiClient;
import pl.jakub.common.TestUserProvider;
import pl.jakub.context.CommonContext;

public class UserHooks {

    private final TestUserProvider testUserProvider;
    private final UserApiClient userApiClient;
    private final CommonContext commonContext;

    public UserHooks(TestUserProvider testUserProvider, UserApiClient userApiClient, CommonContext commonContext) {
        this.testUserProvider = testUserProvider;
        this.userApiClient = userApiClient;
        this.commonContext = commonContext;
    }

    @Before("@requiresApiUser")
    public void createUserViaApi() {
        var registration = testUserProvider.newUniqueRegistrationData();
        userApiClient.createUser(registration);

        var creds = testUserProvider.toCredentials(registration);
        commonContext.setUser(creds);
    }
}
