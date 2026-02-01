package pl.jakub.ui.assertions.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.stereotype.Component;
import pl.jakub.ui.assertions.SignUpAssertions;
import pl.jakub.ui.pages.SignUpPage;

@Component
public class SignUpAssertionsImpl implements SignUpAssertions {

	private final SignUpPage signUpPage;

	public SignUpAssertionsImpl(SignUpPage signUpPage) {
		this.signUpPage = signUpPage;
	}

	@Override
	public void assertProvidedInformation(String expectedName, String expectedEmail) {
		assertEquals(expectedEmail, signUpPage.getEmailValue());
		assertEquals(expectedName, signUpPage.getNameValue());
	}
}
