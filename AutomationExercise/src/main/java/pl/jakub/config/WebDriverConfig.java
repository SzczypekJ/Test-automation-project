package pl.jakub.config;

import io.cucumber.spring.CucumberTestContext;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class WebDriverConfig {

    @Bean(destroyMethod = "quit")
    @Scope(
            value = CucumberTestContext.SCOPE_CUCUMBER_GLUE,
            proxyMode = ScopedProxyMode.TARGET_CLASS
    )
    public WebDriver webDriver(
            @org.springframework.beans.factory.annotation.Value("${ui.headless:false}") boolean headless,
            @org.springframework.beans.factory.annotation.Value("${ui.browserMaximize:true}") boolean maximize,
            @org.springframework.beans.factory.annotation.Value("${ui.windowWidth:1920}") int width,
            @org.springframework.beans.factory.annotation.Value("${ui.windowHeight:1080}") int height
    ) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=" + width + "," + height);
        } else if (maximize) {
            options.addArguments("--start-maximized");
        }

        return new ChromeDriver(options);
    }
}
