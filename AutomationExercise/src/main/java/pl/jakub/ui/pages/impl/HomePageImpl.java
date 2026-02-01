package pl.jakub.ui.pages.impl;

import java.util.List;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.ui.components.HeaderComponent;
import pl.jakub.ui.pages.BasePage;
import pl.jakub.ui.pages.HomePage;

@Component
public class HomePageImpl extends BasePage implements HomePage {

    private final HeaderComponent header;

    private static final By FEATURE_ITEMS = By.xpath("//div[@class='features_items']");

    private static final By CONSENT =
                    By.xpath("//button[@role='button' and contains(@class, 'consent')]");

    public HomePageImpl(CustomWebDriver driver, HeaderComponent header) {
        super(driver);
        this.header = header;
    }

    @Override
    protected String expectedUrl() {
        return "automationexercise.com";
    }

    @Override
    protected List<By> requiredVisibleElements() {
        return List.of(FEATURE_ITEMS);
    }

    @Override
    protected void additionalReadyChecks() {
        header.waitUntilReady();
    }

    public void open() {
        driver.getUrl("https://automationexercise.com/");
        if (driver.waits().isDisplayedNow(CONSENT)) {
            driver.actions().safeClick(CONSENT);
        }
        checkPageReady();
    }

    @Override
    public void openLogin() {
        header.openLogin();
    }

    @Override
    public String getLoggedUserName() {
        return header.getLoggedUserName();
    }

    @Override
    public void deleteAccount() {
        header.deleteAccount();
    }

    @Override
    public void checkIfAccountWasDeletedSuccessfully() {
        checkPageReady();
        if (header.isLoggedUserVisible()) {
            throw new AssertionError("Account deletion failed — user is still logged in");
        }
    }

    @Override
    public boolean isLoggedUserVisible() {
        checkPageReady();
        return header.isLoggedUserVisible();
    }
}
