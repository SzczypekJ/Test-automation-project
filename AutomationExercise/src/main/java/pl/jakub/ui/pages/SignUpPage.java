package pl.jakub.ui.pages;

import pl.jakub.common.RegistrationData;

public interface SignUpPage {

	/** Fetch the name from SignUp form */
	String getNameValue();

	/** Fetch the email from SignUp form */
	String getEmailValue();

	/** Fill registration form details using provided DTO. */
	void fillRegistrationDetails(RegistrationData data);
}
