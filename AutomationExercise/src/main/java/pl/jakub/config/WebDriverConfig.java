package pl.jakub.config;

import io.cucumber.spring.CucumberTestContext;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class WebDriverConfig {

    @Bean(destroyMethod = "quit")
    @Scope(value = CucumberTestContext.SCOPE_CUCUMBER_GLUE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public WebDriver webDriver(
                    @org.springframework.beans.factory.annotation.Value("${ui.headless:false}") boolean headless,
                    @org.springframework.beans.factory.annotation.Value("${ui.browserMaximize:true}") boolean maximize,
                    @org.springframework.beans.factory.annotation.Value("${ui.windowWidth:1920}") int width,
                    @org.springframework.beans.factory.annotation.Value("${ui.windowHeight:1080}") int height,
                    @org.springframework.beans.factory.annotation.Value("${ui.ublockPath:}") String ublockPath) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");

        options.setExperimentalOption("prefs",
                        Map.of("credentials_enable_service",
                                        false,
                                        "profile.password_manager_enabled",
                                        false,
                                        "profile.default_content_setting_values.notifications",
                                        2,
                                        "profile.default_content_setting_values.popups",
                                        2));

        options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=" + width + "," + height);
        } else if (maximize) {
            options.addArguments("--start-maximized");
        }

        if (!headless) {
            Path extDir = resolveUblockDir(ublockPath);
            if (extDir != null) {
                String abs = extDir.toAbsolutePath().toString();

                options.addArguments("--disable-extensions-except=" + abs);
                options.addArguments("--load-extension=" + abs);
            }
        }

        ChromeDriver chrome = new ChromeDriver(options);

        try {
            DevTools devTools = chrome.getDevTools();
            devTools.createSession();
            List<String> blocked = List.of("*://*.doubleclick.net/*",
                            "*://*.googlesyndication.com/*",
                            "*://*.googleadservices.com/*",
                            "*://*.adservice.google.com/*",
                            "*://*/pagead/*",
                            "*://*/ads/*");
            devTools.send(new Command<>("Network.enable", Map.of()));
            devTools.send(new Command<>("Network.setBlockedURLs", Map.of("urls", blocked)));

        } catch (Exception e) {
            System.out.println("[WARN] CDP blockedURLs skipped: " + e.getMessage());
        }

        return chrome;
    }

    private Path resolveUblockDir(String ublockPath) {
        try {
            if (ublockPath != null && !ublockPath.isBlank()) {
                Path fromProp = Path.of(ublockPath).toAbsolutePath();
                if (Files.isRegularFile(fromProp.resolve("manifest.json"))) {
                    return fromProp;
                }
            }

            Path projectRoot = Path.of("").toAbsolutePath();
            Path fromRepo = projectRoot.resolve("ublock");
            if (Files.isRegularFile(fromRepo.resolve("manifest.json"))) {
                return fromRepo.toAbsolutePath();
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
