package pl.jakub.ui.pages;

import java.io.File;

public interface ContactUsPage {

    /**
     * Fills the contact form with the provided details and attachment.
     *
     * @param name    the user's name
     * @param email   the user's email address
     * @param subject the message subject
     * @param message the message content
     * @param file    the file to upload
     */
    void provideFormDetails(String name, String email, String subject, String message, File file);

    /**
     * Fetch the text from pop up after
     */
    String getPopUpText();
}
