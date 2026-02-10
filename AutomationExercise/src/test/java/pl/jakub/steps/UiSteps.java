package pl.jakub.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pl.jakub.common.TestUserProvider;
import pl.jakub.common.UserCredentials;
import pl.jakub.context.CommonContext;
import pl.jakub.ui.assertions.*;
import pl.jakub.ui.pages.*;

import java.io.File;


public class UiSteps {

    private final HomePage homePage;
    private final LoginPage loginPage;
    private final TestUserProvider testUserProvider;
    private final SignUpPage signUpPage;
    private final CreatedAccountPage createdAccountPage;
    private final SignUpAssertions signUpAssertions;
    private final CreatedAccountAssertions createdAccountAssertions;
    private final HomePageAssertions homePageAssertions;
    private final DeletedAccountPage deletedAccountPage;
    private final DeletedAccountAssertions deletedAccountAssertions;
    private final CommonContext commonContext;
    private final LoginPageAssertions loginPageAssertions;
    private final ContactUsPage contactUsPage;
    private final ContactUsAssertions contactUsAssertions;

    public UiSteps(HomePage homePage, LoginPage loginPage, TestUserProvider testUserProvider, SignUpPage signUpPage, CreatedAccountPage createdAccountPage, SignUpAssertions signUpAssertions, CreatedAccountAssertions createdAccountAssertions, HomePageAssertions homePageAssertions, DeletedAccountPage deletedAccountPage, DeletedAccountAssertions deletedAccountAssertions, CommonContext commonContext, LoginPageAssertions loginPageAssertions, ContactUsPage contactUsPage, ContactUsAssertions contactUsAssertions) {
        this.homePage = homePage;
        this.loginPage = loginPage;
        this.testUserProvider = testUserProvider;
        this.signUpPage = signUpPage;
        this.createdAccountPage = createdAccountPage;
        this.signUpAssertions = signUpAssertions;
        this.createdAccountAssertions = createdAccountAssertions;
        this.homePageAssertions = homePageAssertions;
        this.deletedAccountPage = deletedAccountPage;
        this.deletedAccountAssertions = deletedAccountAssertions;
        this.commonContext = commonContext;
        this.loginPageAssertions = loginPageAssertions;
        this.contactUsPage = contactUsPage;
        this.contactUsAssertions = contactUsAssertions;
    }

    @When("user login to the existing account")
    public void user_login_to_the_existing_account() {
        var user = commonContext.getUser();

        homePage.openLogin();
        loginPage.logIntoAccount(user.email(), user.password());
    }


    @Given("user is on the home page")
    public void user_is_on_the_home_page() {
        homePage.open();
    }

    @When("user registers a new account")
    public void user_registers_a_new_account() {
        homePage.openLogin();

        UserCredentials user = testUserProvider.defaultUser();
        loginPage.startSignUp(user.name(), user.email());
        signUpAssertions.assertProvidedInformation(user.name(), user.email());

        signUpPage.fillRegistrationDetails(user.toRegistrationData());
        commonContext.setLoggedFirstTime(true);
    }

    @Then("user is logged in")
    public void user_is_logged_in() {
        if (commonContext.isLoggedFirstTime()) {
            createdAccountAssertions.assertVisibleText("Account Created!");
            createdAccountPage.clickContinue();
        }

        UserCredentials user = testUserProvider.defaultUser();
        homePageAssertions.assertLoggedUser(user.name());
    }

    @When("user deletes the account")
    public void user_deletes_the_account() {
        homePage.deleteAccount();

        deletedAccountAssertions.assertVisibleText("Account Deleted!");
        deletedAccountPage.clickContinue();
    }

    @Then("account is deleted")
    public void account_is_deleted() {
        homePageAssertions.assertAccountDeleted();
    }

    @When("user logs in with incorrect email and password")
    public void user_logs_in_with_incorrect_email_and_password() {
        homePage.openLogin();
        loginPage.logIntoAccount("wrong_email@gmail.com123213123", "WrongPassword12312312");
    }

    @Then("login error {string} is visible")
    public void login_error_is_visible(String expected) {
        loginPageAssertions.assertLoginErrorVisible(expected);
    }

    @When("user logout from the account")
    public void user_logout_from_the_account() {
        homePage.logout();
    }

    @When("user registers to existing account")
    public void user_registers_to_existing_account() {
        var user = commonContext.getUser();

        homePage.openLogin();
        loginPage.startSignUp(user.name(), user.email());
    }

    @Then("user is redirected to the Login Page")
    public void user_is_redirected_to_the_Login_Page() {
        loginPage.waitUntilReady();
    }

    @Then("register error {string} is visible")
    public void register_error_is_visible(String expected) {
        loginPageAssertions.assertRegisterErrorVisible(expected);
    }

    @When("user opens the Contact Us page")
    public void user_opens_the_Contact_Us_page() {
        homePage.openContactUs();
    }

    @And("user submits the contact form with valid data and an attachment")
    public void user_submits_the_contact_form_with_valid_data_and_an_attachment() {
        UserCredentials user = testUserProvider.defaultUser();
        File file = new File("src/test/resources/files/test.txt");
        contactUsPage.provideFormDetails(user.name(), user.email(), "Test", "This is a test", file);
    }

    @Then("success message {string} is visible")
    public void success_message_is_visible(String expected) {
        contactUsAssertions.assertVisibleText(expected);
    }

    @And("user is back on the home page")
    public void user_is_back_on_the_home_page() {
        homePage.openHomePage();
    }
}
