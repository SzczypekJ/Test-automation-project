package pl.jakub.ui.assertions.impl;

import org.springframework.stereotype.Component;
import pl.jakub.ui.assertions.ContactUsAssertions;
import pl.jakub.ui.pages.ContactUsPage;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Component
public class ContactUsAssertionsImpl implements ContactUsAssertions {

    private final ContactUsPage contactUsPage;

    public ContactUsAssertionsImpl(ContactUsPage contactUsPage) {
        this.contactUsPage = contactUsPage;
    }

    @Override
    public void assertVisibleText(String expectedText) {
        assertEquals(expectedText.toLowerCase(), contactUsPage.getPopUpText().toLowerCase());
    }
}
