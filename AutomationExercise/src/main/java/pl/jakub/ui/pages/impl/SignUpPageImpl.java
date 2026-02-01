package pl.jakub.ui.pages.impl;

import java.time.LocalDate;
import java.util.List;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import pl.jakub.common.RegistrationData;
import pl.jakub.common.Title;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.ui.components.HeaderComponent;
import pl.jakub.ui.pages.BasePage;
import pl.jakub.ui.pages.SignUpPage;

@Component
public class SignUpPageImpl extends BasePage implements SignUpPage {

    private final HeaderComponent header;

    private static final By SIGN_UP_FORM = By.xpath("//div[@class='login-form']");

    private static final By EMAIL = By.id("email");

    private static final By NAME = By.id("name");

    private static final By MR_TITLE = By.id("id_gender1");

    private static final By MRS_TITLE = By.id("id_gender2");

    private static final By PASSWORD = By.id("password");

    private static final By DAYS = By.id("days");

    private static final By MONTHS = By.id("months");

    private static final By YEARS = By.id("years");

    private static final By NEWSLETTER = By.id("newsletter");

    private static final By SPECIAL_OFFER = By.id("optin");

    private static final By FIRST_NAME = By.id("first_name");

    private static final By LAST_NAME = By.id("last_name");

    private static final By COMPANY = By.id("company");

    private static final By ADDRESS = By.id("address1");

    private static final By ADDRESS_SECONDARY = By.id("address2");

    private static final By COUNTRY = By.id("country");

    private static final By STATE = By.id("state");

    private static final By CITY = By.id("city");

    private static final By ZIP_CODE = By.id("zipcode");

    private static final By MOBILE_NUMBER = By.id("mobile_number");

    private static final By CREATE_ACCOUNT = By.xpath("//button[@data-qa='create-account']");

    public SignUpPageImpl(CustomWebDriver driver, HeaderComponent header) {
        super(driver);
        this.header = header;
    }

    @Override
    protected String expectedUrl() {
        return "signup";
    }

    @Override
    protected List<By> requiredVisibleElements() {
        return List.of(SIGN_UP_FORM);
    }

    @Override
    protected void additionalReadyChecks() {
        header.waitUntilReady();
    }

    @Override
    public String getEmailValue() {
        return driver.waits().waitForVisibilityOfElement(EMAIL).getAttribute("value");
    }

    @Override
    public void fillRegistrationDetails(RegistrationData data) {
        checkPageReady();
        setTitle(data.title());

        driver.waits().waitForElementToBeClickable(PASSWORD).sendKeys(data.password());

        var dateOfBirth = data.dateOfBirth();
        setDateOfBirth(dateOfBirth);

        setNotificationPreferences();

        driver.waits().waitForElementToBeClickable(FIRST_NAME).sendKeys(data.firstName());
        driver.waits().waitForElementToBeClickable(LAST_NAME).sendKeys(data.lastName());
        driver.waits().waitForElementToBeClickable(COMPANY).sendKeys(data.company());
        driver.waits().waitForElementToBeClickable(ADDRESS).sendKeys(data.address());
        driver.waits().waitForElementToBeClickable(ADDRESS_SECONDARY).sendKeys(data.addressSecond());
        driver.actions().selectByValue(COUNTRY, data.country().uiText());
        driver.waits().waitForElementToBeClickable(STATE).sendKeys(data.state());
        driver.waits().waitForElementToBeClickable(CITY).sendKeys(data.city());
        driver.waits().waitForElementToBeClickable(ZIP_CODE).sendKeys(data.zipCode());
        driver.waits().waitForElementToBeClickable(MOBILE_NUMBER).sendKeys(data.mobileNumber());

        driver.waits().waitForElementToBeClickable(CREATE_ACCOUNT).click();
        driver.actions().closeVignetteAdIfPresent();
    }

    private void setTitle(Title title) {
        if (title == Title.MR) {
            driver.waits().waitForElementToBeClickable(MR_TITLE).click();
        } else if (title == Title.MRS) {
            driver.waits().waitForElementToBeClickable(MRS_TITLE).click();
        } else {
            throw new IllegalArgumentException("Unknown title provided: " + title);
        }
    }

    private void setDateOfBirth(LocalDate dateOfBirth) {
        driver.actions().selectByValue(DAYS, String.valueOf(dateOfBirth.getDayOfMonth()));
        driver.actions().selectByValue(MONTHS, String.valueOf(dateOfBirth.getMonthValue()));
        driver.actions().selectByValue(YEARS, String.valueOf(dateOfBirth.getYear()));
    }

    private void setNotificationPreferences() {
        driver.waits().waitForElementToBeClickable(NEWSLETTER).click();
        driver.waits().waitForElementToBeClickable(SPECIAL_OFFER).click();
    }

    @Override
    public String getNameValue() {
        return driver.waits().waitForVisibilityOfElement(NAME).getAttribute("value");
    }
}
