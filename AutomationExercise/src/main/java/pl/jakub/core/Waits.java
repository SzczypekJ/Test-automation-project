package pl.jakub.core;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pl.jakub.core.wait.WaitTimeout;

public class Waits {

  private final WebDriver driver;
  private final Duration timeout;
  private final Duration pollEvery;

  public Waits(WebDriver driver, Duration timeout) {
    this(driver, timeout, Duration.ofMillis(100));
  }

  public Waits(WebDriver driver, Duration timeout, Duration pollEvery) {
    this.driver = driver;
    this.timeout = timeout;
    this.pollEvery = pollEvery;
  }

  private FluentWait<WebDriver> waitDriver() {
    return waitDriver(timeout);
  }

  private FluentWait<WebDriver> waitDriver(Duration timeoutOverride) {
    Duration t =
        (timeoutOverride == null || timeoutOverride.isZero() || timeoutOverride.isNegative())
            ? timeout
            : timeoutOverride;

    return new WebDriverWait(driver, t)
        .pollingEvery(pollEvery)
        .ignoring(StaleElementReferenceException.class)
        .ignoring(NoSuchElementException.class);
  }

  public WebElement waitForVisibilityOfElement(By locator) {
    return waitDriver().until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public List<WebElement> waitForVisibilityOfAllElements(By locator) {
    return waitDriver().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  }

  public WebElement waitForElementToBeClickable(By locator) {
    return waitDriver().until(ExpectedConditions.elementToBeClickable(locator));
  }

  public WebElement waitForPresenceOfElement(By locator) {
    return waitDriver().until(ExpectedConditions.presenceOfElementLocated(locator));
  }

  public List<WebElement> waitForPresenceOfAllElements(By locator) {
    return waitDriver().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
  }

  public boolean titleContains(String text) {
    return Boolean.TRUE.equals(waitDriver().until(ExpectedConditions.titleContains(text)));
  }

  public boolean urlContains(String text) {
    return Boolean.TRUE.equals(waitDriver().until(ExpectedConditions.urlContains(text)));
  }

  public boolean textToBePresentIn(By locator, String text) {
    return Boolean.TRUE.equals(
        waitDriver().until(ExpectedConditions.textToBePresentInElementLocated(locator, text)));
  }

  public boolean checkIfElementIsInvisible(By locator) {
    return Boolean.TRUE.equals(
        waitDriver().until(ExpectedConditions.invisibilityOfElementLocated(locator)));
  }

  public void forJsReady() {
    waitDriver()
        .until(
            d -> {
              try {
                Object result =
                    ((JavascriptExecutor) d).executeScript("return document.readyState");
                return "complete".equals(result);
              } catch (Exception e) {
                return false;
              }
            });
  }

  public boolean isElementVisible(By locator, WaitTimeout timeout) {
    try {
      waitDriver(timeout.duration()).until(ExpectedConditions.visibilityOfElementLocated(locator));
      return true;
    } catch (TimeoutException e) {
      return false;
    }
  }

  public boolean isElementVisible(By locator) {
    return isElementVisible(locator, WaitTimeout.DEFAULT);
  }

  public boolean isDisplayedNow(By locator) {
    try {
      var els = driver.findElements(locator); // zero wait
      return !els.isEmpty() && els.get(0).isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }
}
