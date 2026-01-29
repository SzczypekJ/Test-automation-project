package pl.jakub.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.ui.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiSteps {

    private final HomePage homePage;
    private CustomWebDriver driver;

    public UiSteps(HomePage homePage, CustomWebDriver driver) {
        this.homePage = homePage;
        this.driver = driver;
    }

    @Given("user is on the home page")
    public void openHomePage() {
        homePage.open();
    }

    @When("user registers a new account")
    public void user_registers_a_new_account() {
        // TODO: Implement user registration flow via Signup page
        throw new AssertionError("TODO: Step not implemented - user registers a new account");
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
