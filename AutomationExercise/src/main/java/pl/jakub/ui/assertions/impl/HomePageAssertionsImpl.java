package pl.jakub.ui.assertions.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.springframework.stereotype.Component;
import pl.jakub.ui.assertions.HomePageAssertions;
import pl.jakub.ui.pages.HomePage;

@Component
public class HomePageAssertionsImpl implements HomePageAssertions {

    private final HomePage homePage;

    public HomePageAssertionsImpl(HomePage homePage) {
        this.homePage = homePage;
    }

    @Override
    public void assertLoggedUser(String expectedUserName) {
        assertEquals(expectedUserName,
                homePage.getLoggedUserName());
    }

    @Override
    public void assertAccountDeleted() {
        assertFalse(homePage.isLoggedUserVisible(),
                "Account deletion failed — user is still logged in");
    }
}
