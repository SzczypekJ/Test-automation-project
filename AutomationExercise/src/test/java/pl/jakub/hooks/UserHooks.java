package pl.jakub.hooks;

import io.cucumber.java.Before;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jakub.api.assertions.UserApiClientAssertions;
import pl.jakub.api.client.UserApiClient;
import pl.jakub.common.TestUserProvider;
import pl.jakub.context.CommonContext;

public class UserHooks {

    private final TestUserProvider testUserProvider;
    private final UserApiClient userApiClient;
    private final CommonContext commonContext;
    private final UserApiClientAssertions userApiClientAssertions;
    private static final Logger logger = LoggerFactory.getLogger(UserHooks.class);

    public UserHooks(TestUserProvider testUserProvider, UserApiClient userApiClient, CommonContext commonContext, UserApiClientAssertions userApiClientAssertions) {
        this.testUserProvider = testUserProvider;
        this.userApiClient = userApiClient;
        this.commonContext = commonContext;
        this.userApiClientAssertions = userApiClientAssertions;
    }

    @Before("@requiresApiUser")
    public void createUserViaApi() {

        int maxAttempts = 5;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                logger.info("Creating unique user (attempt {}/{})", attempt, maxAttempts);

                var registration = testUserProvider.newUniqueRegistrationData();
                Response response = userApiClient.createUser(registration);

                userApiClientAssertions.assertUserCreated(response);

                var creds = testUserProvider.toCredentials(registration);
                commonContext.setUser(creds);

                logger.info("User created successfully on attempt {}", attempt);
                return;

            } catch (AssertionError e) {

                logger.warn("Attempt {} failed: {}", attempt, e.getMessage());

                if (attempt == maxAttempts) {
                    throw new AssertionError(
                            "Failed to create user after "
                                    + maxAttempts
                                    + " attempts. Last error: "
                                    + e.getMessage(),
                            e
                    );
                }

                logger.info("Retrying user creation...");
            }
        }
    }
}
