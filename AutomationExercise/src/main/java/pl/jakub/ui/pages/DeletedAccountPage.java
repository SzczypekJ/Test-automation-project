package pl.jakub.ui.pages;

public interface DeletedAccountPage {

    /**
     * Fetch the text from pop up after account delete
     */
    String getPopUpText();

    /**
     * Clicks the Continue button on the current page.
     */
    void clickContinue();
}
