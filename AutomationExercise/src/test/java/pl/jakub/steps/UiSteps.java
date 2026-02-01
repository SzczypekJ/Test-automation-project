package pl.jakub.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pl.jakub.common.TestUserProvider;
import pl.jakub.common.UserCredentials;
import pl.jakub.ui.assertions.CreatedAccountAssertions;
import pl.jakub.ui.assertions.DeletedAccountAssertions;
import pl.jakub.ui.assertions.HomePageAssertions;
import pl.jakub.ui.assertions.SignUpAssertions;
import pl.jakub.ui.pages.*;

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

	public UiSteps(HomePage homePage, LoginPage loginPage, TestUserProvider testUserProvider, SignUpPage signUpPage,
			CreatedAccountPage createdAccountPage, SignUpAssertions signUpAssertions,
			CreatedAccountAssertions createdAccountAssertions, HomePageAssertions homePageAssertions,
			DeletedAccountPage deletedAccountPage, DeletedAccountAssertions deletedAccountAssertions) {
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
		signUpAssertions.assertProvidedInformation(user.name(), user.email());

		signUpPage.fillRegistrationDetails(user.toRegistrationData());
	}

	@Then("user is logged in")
	public void user_is_logged_in() {
		createdAccountAssertions.assertVisibleText("Account Created!");
		createdAccountPage.clickContinue();

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
		homePage.checkIfAccountWasDeletedSuccessfully();
	}
}
