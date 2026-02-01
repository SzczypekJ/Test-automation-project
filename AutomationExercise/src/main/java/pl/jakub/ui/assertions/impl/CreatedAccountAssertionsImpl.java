package pl.jakub.ui.assertions.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.stereotype.Component;
import pl.jakub.ui.assertions.CreatedAccountAssertions;
import pl.jakub.ui.pages.CreatedAccountPage;

@Component
public class CreatedAccountAssertionsImpl implements CreatedAccountAssertions {

	private final CreatedAccountPage createdAccountPage;

	public CreatedAccountAssertionsImpl(CreatedAccountPage createdAccountPage) {
		this.createdAccountPage = createdAccountPage;
	}

	@Override
	public void assertVisibleText(String expectedText) {
		assertEquals(expectedText.toLowerCase(), createdAccountPage.getPopUpText().toLowerCase());
	}
}
