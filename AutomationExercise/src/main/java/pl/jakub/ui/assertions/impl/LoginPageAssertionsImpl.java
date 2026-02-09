package pl.jakub.ui.assertions.impl;

import org.springframework.stereotype.Component;
import pl.jakub.ui.assertions.LoginPageAssertions;
import pl.jakub.ui.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class LoginPageAssertionsImpl implements LoginPageAssertions {
    private final LoginPage loginPage;

    public LoginPageAssertionsImpl(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Override
    public void assertLoginErrorVisible(String expected) {
        assertEquals(
                expected.toLowerCase(),
                loginPage.getLoginErrorText().toLowerCase(),
                "Unexpected login error message"
        );
    }

    @Override
    public void assertRegisterErrorVisible(String expected) {
        assertEquals(
                expected.toLowerCase(),
                loginPage.getRegisterErrorText().toLowerCase(),
                "Unexpected login error message"
        );
    }
}
