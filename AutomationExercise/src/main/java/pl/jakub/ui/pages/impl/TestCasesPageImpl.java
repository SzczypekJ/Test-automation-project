package pl.jakub.ui.pages.impl;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import pl.jakub.core.CustomWebDriver;
import pl.jakub.ui.pages.BasePage;
import pl.jakub.ui.pages.TestCasesPage;

import java.util.List;

@Component
public class TestCasesPageImpl extends BasePage implements TestCasesPage {

    public static final By TEST_CASES = By.xpath("//div[@class='container']//h2[contains(@class, 'title')]");

    public TestCasesPageImpl(CustomWebDriver driver) {
        super(driver);
    }

    @Override
    protected String expectedUrl() {
        return "test_cases";
    }

    @Override
    protected List<By> requiredVisibleElements() {
        return List.of(
                TEST_CASES
        );
    }

    @Override
    public void checkThatPageIsFullyLoaded() {
        checkPageReady();
    }
}
