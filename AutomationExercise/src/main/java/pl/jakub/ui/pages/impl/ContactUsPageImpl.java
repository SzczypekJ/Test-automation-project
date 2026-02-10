package pl.jakub.ui.pages.impl;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.core.wait.WaitTimeout;
import pl.jakub.ui.pages.BasePage;
import pl.jakub.ui.pages.ContactUsPage;

import java.io.File;
import java.util.List;

@Component
public class ContactUsPageImpl extends BasePage implements ContactUsPage {

    private static final By GET_IN_TOUCH = By.xpath("//div[@class='contact-form']//h2[contains(@class, 'title')]");

    private static final By CONTACT_US = By.xpath("//div[@class='col-sm-12']//h2[contains(@class, 'title')]");

    private static final By SUBMIT_BUTTON = By.xpath("//input[@data-qa='submit-button']");

    private static final By NAME = By.xpath("//input[@data-qa='name']");

    private static final By EMAIL = By.xpath("//input[@data-qa='email']");

    private static final By SUBJECT = By.xpath("//input[@data-qa='subject']");

    private static final By MESSAGE = By.xpath("//textarea[@data-qa='message']");

    private static final By CHOOSE_FILE = By.xpath("//input[@type='file']");

    private static final By FORM_SUBMITTED_SUCCESSFULLY = By.xpath("//div[contains(@class, 'alert')]");

    public ContactUsPageImpl(CustomWebDriver driver) {
        super(driver);
    }

    @Override
    protected String expectedUrl() {
        return "contact_us";
    }

    @Override
    protected List<By> requiredVisibleElements() {
        return List.of(
                GET_IN_TOUCH,
                CONTACT_US,
                SUBMIT_BUTTON
        );
    }

    @Override
    public void provideFormDetails(String name, String email, String subject, String message, File file) {
        checkPageReady();
        driver.waits().waitForElementToBeClickable(NAME).sendKeys(name);
        driver.waits().waitForElementToBeClickable(EMAIL).sendKeys(email);
        driver.waits().waitForElementToBeClickable(SUBJECT).sendKeys(subject);
        driver.waits().waitForElementToBeClickable(MESSAGE).sendKeys(message);

        String path = file.getAbsolutePath();
        driver.waits().waitForVisibilityOfElement(CHOOSE_FILE).sendKeys(path);
        driver.waits().waitForElementToBeClickable(SUBMIT_BUTTON).click();

        driver.acceptAlert(WaitTimeout.SHORT);
    }

    @Override
    public String getPopUpText() {
        return driver.waits().waitForVisibilityOfElement(FORM_SUBMITTED_SUCCESSFULLY).getText();
    }
}
