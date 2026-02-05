package pl.jakub.ui.pages;

public interface CreatedAccountPage {

    /**
     * Fetch the text from pop up after account creation
     */
    String getPopUpText();

    /**
     * Clicks the Continue button on the current page.
     */
    void clickContinue();
}
