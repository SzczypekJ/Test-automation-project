package pl.jakub.ui.pages;

public interface LoginPage {

    /**
     * Enters the user's name and email into the sign-up form.
     *
     * @param name  the user's name
     * @param email the user's email address
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
     * Returns the register error message displayed on the login form.
     *
     * @return visible register error text
     */
    String getRegisterErrorText();

    /**
     * Checks if Login Page is fully loaded
     */
    void waitUntilReady();
}
