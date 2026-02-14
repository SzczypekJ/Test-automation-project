package pl.jakub.ui.components.impl;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.core.wait.WaitTimeout;
import pl.jakub.ui.components.HeaderComponent;

@Component
public class HeaderComponentImpl implements HeaderComponent {

    private final CustomWebDriver driver;

    private static final By SIGNUP_LOGIN = By.xpath("//ul[contains(@class, 'nav')]//li//a[@href='/login']");

    private static final By DELETE_ACCOUNT = By.xpath("//ul[contains(@class,'nav')]//a[@href='/delete_account']");

    private static final By LOGGED_USER = By.xpath("//li[a[contains(.,'Logged in as')]]//b");

    private static final By HOME = By.xpath("//ul[contains(@class, 'nav')]//li//a[@href='/']");

    private static final By LOGO = By.xpath("//div[contains(@class, 'logo')]");

    private static final By LOGOUT = By.xpath("//ul[contains(@class,'nav')]//a[@href='/logout']");

    private static final By CONTACT_US = By.xpath("//ul[contains(@class,'nav')]//a[@href='/contact_us']");

    private static final By TEST_CASES = By.xpath("//ul[contains(@class,'nav')]//a[@href='/test_cases']");

    public HeaderComponentImpl(CustomWebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void waitUntilReady() {
        driver.waits().waitForVisibilityOfElement(LOGO);
        driver.waits().waitForElementToBeClickable(HOME);
    }

    @Override
    public void openLogin() {
        driver.waits().waitForElementToBeClickable(SIGNUP_LOGIN).click();
    }

    @Override
    public String getLoggedUserName() {
        return driver.waits()
                .waitForVisibilityOfElement(LOGGED_USER)
                .getText()
                .trim();
    }

    @Override
    public void deleteAccount() {
        driver.waits().waitForElementToBeClickable(DELETE_ACCOUNT).click();
    }

    @Override
    public void openHome() {
        driver.waits().waitForElementToBeClickable(HOME).click();
    }

    @Override
    public boolean isLoggedUserVisible() {
        return driver.waits().isDisplayedNow(LOGGED_USER);
    }

    @Override
    public void logout() {
        driver.waits().waitForElementToBeClickable(LOGOUT).click();
    }

    @Override
    public void openContactUs() {
        driver.waits().waitForElementToBeClickable(CONTACT_US).click();
    }

    @Override
    public void openTestCases() {
        driver.waits().waitForElementToBeClickable(TEST_CASES).click();
    }
}
