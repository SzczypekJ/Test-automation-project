package pl.jakub.ui.pages.impl;

import java.util.List;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.ui.components.HeaderComponent;
import pl.jakub.ui.pages.BasePage;
import pl.jakub.ui.pages.DeletedAccountPage;

@Component
public class DeletedAccountPageImpl extends BasePage implements DeletedAccountPage {

    private final HeaderComponent header;

    private static final By ACCOUNT_DELETED = By.xpath("//*[@data-qa='account-deleted']");

    private static final By CONTINUE_BUTTON = By.xpath("//a[@data-qa='continue-button']");

    public DeletedAccountPageImpl(CustomWebDriver driver, HeaderComponent header) {
        super(driver);
        this.header = header;
    }

    @Override
    protected String expectedUrl() {
        return "delete_account";
    }

    @Override
    protected List<By> requiredVisibleElements() {
        return List.of(
                ACCOUNT_DELETED,
                CONTINUE_BUTTON
        );
    }

    @Override
    protected void additionalReadyChecks() {
        header.waitUntilReady();
    }

    @Override
    public String getPopUpText() {
        checkPageReady();
        return driver.waits().waitForVisibilityOfElement(ACCOUNT_DELETED).getText();
    }

    @Override
    public void clickContinue() {
        driver.waits().waitForElementToBeClickable(CONTINUE_BUTTON).click();
    }
}
