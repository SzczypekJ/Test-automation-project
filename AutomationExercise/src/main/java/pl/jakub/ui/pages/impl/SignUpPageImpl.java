package pl.jakub.ui.pages.impl;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import pl.jakub.common.RegistrationData;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.ui.pages.BasePage;
import pl.jakub.ui.pages.SignUpPage;
import java.util.List;

@Component
public class SignUpPageImpl extends BasePage implements SignUpPage {

    private static final By SIGN_UP_FORM = By.xpath("//div[@class='login-form']");

    private static final By EMAIL = By.id("email");

    private static final By NAME = By.id("name");

    public SignUpPageImpl(CustomWebDriver driver) {
        super(driver);
    }

    @Override
    protected String expectedUrl() {
        return "signup";
    }

    @Override
    protected List<By> requiredVisibleElements() {
        return List.of(
                SIGN_UP_FORM
        );
    }


    @Override
    public String getEmailValue() {
        return driver.waits()
                .waitForVisibilityOfElement(EMAIL)
                .getAttribute("value");
    }

    @Override
    public void fillRegistrationDetails(RegistrationData data) {
        // TODO: Write a function
    }

    @Override
    public String getNameValue() {
        return driver.waits()
                .waitForVisibilityOfElement(NAME)
                .getAttribute("value");
    }
}
