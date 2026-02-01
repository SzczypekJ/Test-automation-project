package pl.jakub.hooks;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;
import pl.jakub.config.TestSpringConfig;

@CucumberContextConfiguration
@ContextConfiguration(classes = TestSpringConfig.class)
public class CucumberSpringConfiguration {
}
