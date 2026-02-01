package pl.jakub.common;

public enum Country {
    INDIA("India"),
    UNITED_STATES("United States"),
    CANADA("Canada"),
    AUSTRALIA("Australia"),
    ISRAEL("Israel"),
    NEW_ZEALAND("New Zealand"),
    SINGAPORE("Singapore");

    private final String uiText;

    Country(String uiText) {
        this.uiText = uiText;
    }

    public String uiText() {
        return uiText;
    }
}
