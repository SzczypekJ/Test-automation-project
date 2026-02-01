package pl.jakub.ui.assertions.impl;

import org.springframework.stereotype.Component;
import pl.jakub.ui.assertions.DeletedAccountAssertions;
import pl.jakub.ui.pages.CreatedAccountPage;
import pl.jakub.ui.pages.DeletedAccountPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class DeletedAccountAssertionsImpl implements DeletedAccountAssertions {
    private final DeletedAccountPage deletedAccountPage;

    public DeletedAccountAssertionsImpl(DeletedAccountPage deletedAccountPage) {
        this.deletedAccountPage = deletedAccountPage;
    }

    @Override
    public void assertVisibleText(String expectedText) {
        assertEquals(expectedText.toLowerCase(), deletedAccountPage.getPopUpText().toLowerCase());
    }
}
