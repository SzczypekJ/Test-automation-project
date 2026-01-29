package pl.jakub.core;

import org.openqa.selenium.*;
import pl.jakub.core.wait.WaitTimeout;

import java.util.List;

public class CustomWebDriver {
    private final WebDriver driver;
    private final Waits waits;
    private final UiActions actions;

    public CustomWebDriver(WebDriver driver, Waits waits, UiActions actions) {
        this.driver = driver;
        this.waits = waits;
        this.actions = actions;
    }

    public WebDriver raw() {
        return driver;
    }

    public Waits waits() {
        return waits;
    }

    public UiActions actions() {
        return actions;
    }

    public List<WebElement> waitForPresenceOfAllElements(By locator) {
        return waits.waitForPresenceOfAllElements(locator);
    }

    public WebElement waitForVisibilityOfElement(By locator) {
        return waits.waitForVisibilityOfElement(locator);
    }

    public void safeClick(By locator) {
        actions.safeClick(locator);
    }

    public void clearAndType(By locator, String text) {
        actions.clearAndType(locator, text);
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }


    public boolean isVisible(By locator) {
        return waits.isElementVisible(locator);
    }

    public boolean isVisible(By locator, WaitTimeout timeout) {
        return waits.isElementVisible(locator, timeout);
    }

    public void click(By locator) {
        waits.waitForElementToBeClickable(locator).click();
    }
}
