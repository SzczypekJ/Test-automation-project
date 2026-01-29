package pl.jakub.ui.pages;

public interface HomePage {

    /**
     * Opens the Home Page and waits until it is ready for interaction.
     */
    public void open();

    /*
     * Method which is checking if current page contains expected URL
     * */
    public boolean isTitleContaining(String expected);
}
