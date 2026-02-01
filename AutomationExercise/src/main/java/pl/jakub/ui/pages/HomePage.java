package pl.jakub.ui.pages;

public interface HomePage {

    /** Opens the Home Page and waits until it is ready for interaction. */
    void open();

    /** Opens Login Page */
    void openLogin();

    /** Fetch the logged username */
    String getLoggedUserName();

    /** Click on delete account button */
    void deleteAccount();

    /** Verifies that account was deleted correctly */
    void checkIfAccountWasDeletedSuccessfully();

    /** Checks whether logged user label is visible */
    boolean isLoggedUserVisible();
}
