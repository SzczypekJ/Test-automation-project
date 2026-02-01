package pl.jakub.ui.pages.impl;

import java.util.List;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.ui.components.HeaderComponent;
import pl.jakub.ui.pages.BasePage;
import pl.jakub.ui.pages.LoginPage;

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
        return List.of(LOGIN_FORM,
                SIGNUP_FORM);
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
}
