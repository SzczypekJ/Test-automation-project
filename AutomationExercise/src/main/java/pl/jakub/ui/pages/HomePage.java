package pl.jakub.ui.pages;

public interface HomePage {

    /**
     * Opens the Home Page and waits until it is ready for interaction.
     */
    void open();

    /**
     * Opens Login Page
     */
    void openLogin();

    /**
     * Fetch the logged username
     */
    String getLoggedUserName();

    /**
     * Click on delete account button
     */
    void deleteAccount();

    /**
     * Checks whether logged user label is visible
     */
    boolean isLoggedUserVisible();

    /**
     * Click on logout button
     */
    void logout();

    /**
     * Opens Contact Us Page
     */
    void openContactUs();

    /**
     * Opens Home Page
     */
    void openHomePage();

    /**
     * Opens Test Case Page
     */
    void openTestCasePage();
}
