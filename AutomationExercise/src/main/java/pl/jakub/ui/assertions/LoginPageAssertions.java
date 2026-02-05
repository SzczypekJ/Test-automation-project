package pl.jakub.ui.assertions;

public interface LoginPageAssertions {

    /**
     * Verifies that the login error message matches the expected text.
     *
     * @param expected expected login error message
     */
    void assertLoginErrorVisible(String expected);
}
