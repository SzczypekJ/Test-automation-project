package pl.jakub.config;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.core.UiActions;
import pl.jakub.core.Waits;

@Configuration
@PropertySource("classpath:application.properties")
public class UiSupportConfig {

  @Bean
  public Waits waits(WebDriver webDriver, @Value("${ui.timeoutSeconds:10}") long timeoutSeconds) {
    return new Waits(webDriver, Duration.ofSeconds(timeoutSeconds));
  }

  @Bean
  public UiActions uiActions(WebDriver webDriver, Waits waits) {
    return new UiActions(webDriver, waits);
  }

  @Bean
  public CustomWebDriver customWebDriver(WebDriver webDriver, Waits waits, UiActions uiActions) {
    return new CustomWebDriver(webDriver, waits, uiActions);
  }
}
