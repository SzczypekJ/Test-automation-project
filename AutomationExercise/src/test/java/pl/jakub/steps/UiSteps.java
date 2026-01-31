package pl.jakub.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pl.jakub.common.TestUserProvider;
import pl.jakub.common.UserCredentials;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.ui.pages.HomePage;
import pl.jakub.ui.pages.LoginPage;
import pl.jakub.ui.pages.SignUpPage;


public class UiSteps {

    private final HomePage homePage;
    private final LoginPage loginPage;
    private CustomWebDriver driver;
    private final TestUserProvider testUserProvider;
    private final SignUpPage signUpPage;

    public UiSteps(HomePage homePage, LoginPage loginPage, CustomWebDriver driver, TestUserProvider testUserProvider, SignUpPage signUpPage) {
        this.homePage = homePage;
        this.loginPage = loginPage;
        this.driver = driver;
        this.testUserProvider = testUserProvider;
        this.signUpPage = signUpPage;
    }

    @Given("user is on the home page")
    public void openHomePage() {
        homePage.open();
    }

    @When("user registers a new account")
    public void user_registers_a_new_account() {
        homePage.openLogin();

        UserCredentials user = testUserProvider.defaultUser();
        loginPage.startSignUp(user.name(), user.email());

        signUpPage.fillRegistrationDetails(user.toRegistrationData());
        String smth1 = "o";
    }

    @Then("user is logged in")
    public void user_is_logged_in() {
        // TODO: Verify user is logged in (e.g. 'Logged in as username' visible)
        throw new AssertionError("TODO: Step not implemented - user is logged in");
    }

    @When("user deletes the account")
    public void user_deletes_the_account() {
        // TODO: Implement account deletion via Account page
        throw new AssertionError("TODO: Step not implemented - user deletes the account");
    }

    @Then("account is deleted")
    public void account_is_deleted() {
        // TODO: Verify account deletion confirmation message
        throw new AssertionError("TODO: Step not implemented - account is deleted");
    }

}
