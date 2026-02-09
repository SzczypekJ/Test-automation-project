package pl.jakub.ui.pages.impl;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.ui.components.HeaderComponent;
import pl.jakub.ui.pages.BasePage;
import pl.jakub.ui.pages.LoginPage;

import java.util.List;

@Component
public class LoginPageImpl extends BasePage implements LoginPage {

    private final HeaderComponent header;

    private static final By LOGIN_FORM = By.xpath("//div[@class='login-form']");

    private static final By SIGNUP_FORM = By.xpath("//div[@class='signup-form']");

    private static final By LOGIN_BUTTON = By.xpath("//button[@data-qa='login-button']");

    private static final By SIGNUP_BUTTON = By.xpath("//button[@data-qa='signup-button']");

    private static final By LOGIN_EMAIL_INPUT = By.xpath("//input[@data-qa='login-email']");

    private static final By LOGIN_PASSWORD_INPUT = By.xpath("//input[@data-qa='login-password']");

    private static final By SIGNUP_EMAIL_INPUT = By.xpath("//input[@data-qa='signup-email']");

    private static final By SIGNUP_NAME_INPUT = By.xpath("//input[@data-qa='signup-name']");

    private static final By LOGIN_ERROR = By.xpath("//form[@action='/login']//p[@style='color: red;']");

    private static final By REGISTER_ERROR = By.xpath("//form[@action='/signup']//p[@style='color: red;']");

    public LoginPageImpl(CustomWebDriver driver, HeaderComponent header) {
        super(driver);
        this.header = header;
    }

    @Override
    protected String expectedUrl() {
        return "login";
    }

    @Override
    protected List<By> requiredVisibleElements() {
        return List.of(
                LOGIN_FORM,
                SIGNUP_FORM
        );
    }

    @Override
    protected void additionalReadyChecks() {
        header.waitUntilReady();
    }


    @Override
    public void startSignUp(String name, String email) {
        checkPageReady();
        driver.waits().waitForElementToBeClickable(SIGNUP_NAME_INPUT).sendKeys(name);
        driver.waits().waitForElementToBeClickable(SIGNUP_EMAIL_INPUT).sendKeys(email);
        driver.waits().waitForElementToBeClickable(SIGNUP_BUTTON).click();
    }

    @Override
    public void logIntoAccount(String email, String password) {
        checkPageReady();
        driver.waits().waitForElementToBeClickable(LOGIN_EMAIL_INPUT).sendKeys(email);
        driver.waits().waitForElementToBeClickable(LOGIN_PASSWORD_INPUT).sendKeys(password);
        driver.waits().waitForElementToBeClickable(LOGIN_BUTTON).click();
    }

    @Override
    public String getLoginErrorText() {
        checkPageReady();
        return driver.waits().waitForVisibilityOfElement(LOGIN_ERROR).getText();
    }

    @Override
    public String getRegisterErrorText() {
        return driver.waits().waitForVisibilityOfElement(REGISTER_ERROR).getText();
    }

    @Override
    public void waitUntilReady() {
        checkPageReady();
    }
}
