package pl.jakub.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pl.jakub.core.CustomWebDriver;

public class ScreenshotHook {

  private final CustomWebDriver customWebDriver;

  public ScreenshotHook(CustomWebDriver customWebDriver) {
    this.customWebDriver = customWebDriver;
  }

  @After
  public void attachArtifactsOnFailure(Scenario scenario) {
    if (!scenario.isFailed())
      return;

    WebDriver driver = unwrap(customWebDriver.raw());

    // (optional) URL
    try {
      String url = driver.getCurrentUrl();
      Allure.addAttachment(scenario.getName() + " - url", "text/plain",
          new ByteArrayInputStream(url.getBytes(StandardCharsets.UTF_8)), ".txt");
    } catch (Exception ignored) {
    }

    // HTML (page source)
    try {
      String html = driver.getPageSource();
      Allure.addAttachment(scenario.getName() + " - page source", "text/html",
          new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8)), ".html");
    } catch (Exception ignored) {
    }

    // Screenshot
    try {
      if (driver instanceof TakesScreenshot ts) {
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(scenario.getName() + " - screenshot", "image/png",
            new ByteArrayInputStream(screenshot), ".png");
      }
    } catch (Exception ignored) {
    }
  }

  /** Works for both: real driver and Spring AOP proxies (JDK/CGLIB). */
  private WebDriver unwrap(WebDriver driver) {
    if (driver instanceof TakesScreenshot)
      return driver;

    try {
      if (driver instanceof org.springframework.aop.framework.Advised advised) {
        Object target = advised.getTargetSource().getTarget();
        if (target instanceof WebDriver)
          return (WebDriver) target;
      }
    } catch (Exception ignored) {
    }

    return driver;
  }
}
