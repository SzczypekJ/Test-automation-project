package pl.jakub.ui.assertions;

public interface HomePageAssertions {
	/**
	 * Verifies that the logged username is correct
	 *
	 * @param expectedUserName
	 *            expected text
	 */
	void assertLoggedUser(String expectedUserName);

	/**
	 * Verifies that account was deleted successfully (logged user label is no
	 * longer visible)
	 */
	void assertAccountDeleted();
}
