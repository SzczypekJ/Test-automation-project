package pl.jakub.ui.assertions;

public interface ContactUsAssertions {

    /**
     * Verifies that the text on pop up matches expected String
     *
     * @param expectedText  expected text
     */
    void assertVisibleText(String expectedText);
}
