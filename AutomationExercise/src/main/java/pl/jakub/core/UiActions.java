package pl.jakub.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class UiActions {

    private final WebDriver driver;
    private final Waits waits;

    private static final By VIGNETTE_CLOSE_BUTTON =
                                    By.xpath("//*[self::button or self::div or self::span]"
                                                                    + "[normalize-space()='Close' or @aria-label='Close' or @title='Close']");

    private static final By VIGNETTE_OVERLAY =
                                    By.cssSelector("body > div[role='dialog'], iframe[id*='google'], iframe[src*='google']");

    public UiActions(WebDriver driver, Waits waits) {
        this.driver = driver;
        this.waits = waits;
    }

    public void scrollIntoView(By locator) {
        WebElement el = waits.waitForPresenceOfElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                                        "arguments[0].scrollIntoView({block:'center'});",
                                        el);
    }

    public void jsClick(By locator) {
        WebElement el = waits.waitForPresenceOfElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    public void type(By locator, String text) {
        WebElement el = waits.waitForVisibilityOfElement(locator);
        el.sendKeys(text);
    }

    public void clearAndType(By locator, String text) {
        WebElement el = waits.waitForVisibilityOfElement(locator);
        el.click();
        el.clear();
        el.sendKeys(text);
    }

    /**
     * Click “safe”: 1) waits until the element is clickable and clicks normally 2) if something is covering it / constantly
     * / intercept -> scroll + JS click
     */
    public void safeClick(By locator) {
        try {
            waits.waitForElementToBeClickable(locator).click();
        } catch (ElementClickInterceptedException | StaleElementReferenceException
                                        | TimeoutException e) {
            scrollIntoView(locator);
            jsClick(locator);
        }
    }

    public void hover(By locator) {
        WebElement el = waits.waitForVisibilityOfElement(locator);
        new Actions(driver).moveToElement(el).perform();
    }

    public void selectByValue(By locator, String value) {
        WebElement el = waits.waitForElementToBeClickable(locator);
        new Select(el).selectByValue(value);
    }

    public void selectByVisibleText(By locator, String text) {
        WebElement el = waits.waitForElementToBeClickable(locator);
        new Select(el).selectByVisibleText(text);
    }

    public void closeVignetteAdIfPresent() {
        try {
            if (!isPresent(VIGNETTE_CLOSE_BUTTON)) {
                return;
            }
            jsClick(VIGNETTE_CLOSE_BUTTON);
        } catch (Exception ignored) {
        }
    }

    private boolean isPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
