package pl.jakub.ui.pages.impl;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.core.wait.WaitTimeout;
import pl.jakub.ui.pages.BasePage;
import pl.jakub.ui.pages.HomePage;

import java.util.List;

@Component
public class HomePageImpl extends BasePage implements HomePage {

    private static final By LOGO = By.xpath("//div[contains(@class, 'logo')]");

    private static final By HOME = By.xpath("//ul[contains(@class, 'nav')]//li//a[@href='/']");

    private static final By SIGNUP_LOGIN = By.xpath("//ul[contains(@class, 'nav')]//li//a[@href='/login']");

    private static final By FEATURE_ITEMS = By.xpath("//div[@class='features_items']");

    private static final By CONSENT = By.xpath("//button[@role='button' and contains(@class, 'consent')]");

    public HomePageImpl(CustomWebDriver driver) {
        super(driver);
    }

    @Override
    protected String expectedUrl() {
        return "automationexercise.com";
    }

    @Override
    protected List<By> requiredVisibleElements() {
        return List.of(
                LOGO,
                HOME,
                SIGNUP_LOGIN,
                FEATURE_ITEMS
        );
    }

    @Override
    protected void additionalReadyChecks() {
        driver.waits().waitForElementToBeClickable(SIGNUP_LOGIN);
    }

    public void open() {
        driver.getUrl("https://automationexercise.com/");
        if (driver.isVisible(CONSENT, WaitTimeout.MEDIUM)) {
            driver.click(CONSENT);
        }
        checkPageReady();
    }

    @Override
    public void openLogin() {
        driver.waits().waitForElementToBeClickable(SIGNUP_LOGIN).click();
    }
}
