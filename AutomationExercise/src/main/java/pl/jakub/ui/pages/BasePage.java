package pl.jakub.ui.pages;

import java.util.List;
import org.openqa.selenium.By;
import pl.jakub.core.CustomWebDriver;

public abstract class BasePage {

	protected final CustomWebDriver driver;

	public BasePage(CustomWebDriver driver) {
		this.driver = driver;
	}

	/** current URL contains at least expected part of address */
	protected abstract String expectedUrl();

	/**
	 * Required elements which needs to be visible to assume that the page is loaded
	 */
	protected abstract List<By> requiredVisibleElements();

	/** Any needed additional checks */
	protected void additionalReadyChecks() {
	}

	public void checkPageReady() {
		driver.waits().urlContains(expectedUrl());

		for (By locator : requiredVisibleElements()) {
			driver.waits().waitForVisibilityOfElement(locator);
		}

		additionalReadyChecks();
	}
}
