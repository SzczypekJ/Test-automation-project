package pl.jakub.ui.pages;

public interface LoginPage {

    /**
     * Provides name and email to the Signup form
     */
    void startSignUp(String name, String email);

    /**
     * Login into existing account
     */
    void logIntoAccount(String email, String password);

    /**
     * Returns the login error message displayed on the login form.
     *
     * @return visible login error text
     */
    String getLoginErrorText();

    /**
     * Checks if Login Page is fully loaded
     */
    void waitUntilReady();
}
