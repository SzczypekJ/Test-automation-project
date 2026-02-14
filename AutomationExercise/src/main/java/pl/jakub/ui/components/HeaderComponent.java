package pl.jakub.ui.components;

public interface HeaderComponent {

    /**
     * Waits until header is visible/ready for interaction.
     * Use it as a page readiness indicator.
     */
    void waitUntilReady();

    /**
     * Opens Login page using header navigation link.
     */
    void openLogin();

    /**
     * Fetch the logged username displayed in the header:
     * "Logged in as <b>USERNAME</b>"
     *
     * @return logged user's name (trimmed)
     */
    String getLoggedUserName();

    /**
     * Clicks "Delete Account" in the header.
     */
    void deleteAccount();

    /**
     * Clicks Home link in the header.
     */
    void openHome();

    /**
     * Checks whether logged user label is visible in header
     */
    boolean isLoggedUserVisible();

    /**
     * Clicks on logout button in the header
     */
    void logout();

    /**
     * Opens Contact Us page using header navigation link.
     */
    void openContactUs();

    /**
     * Opens Test Cases page using header navigation link.
     */
    void openTestCases();
}
