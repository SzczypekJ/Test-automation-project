package pl.jakub.ui.assertions;

public interface SignUpAssertions {

    /**
     * Verifies that values pre-filled on the Sign Up page
     * match the expected user information provided during registration.
     *
     * @param expectedName  expected user name
     * @param expectedEmail expected user email address
     */
    void assertProvidedInformation(String expectedName, String expectedEmail);
}
