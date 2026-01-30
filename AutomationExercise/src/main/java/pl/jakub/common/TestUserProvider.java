package pl.jakub.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestUserProvider {
    private final String name;
    private final String email;
    private final String password;


    public TestUserProvider(
            @Value("${test.user.name}") String name,
            @Value("${test.user.email}") String email,
            @Value("${test.user.password}") String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserCredentials defaultUser() {
        return new UserCredentials(name, email, password);
    }
}
