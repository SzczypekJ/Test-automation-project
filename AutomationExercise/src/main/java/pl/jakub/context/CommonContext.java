package pl.jakub.context;

import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(
    CucumberTestContext.SCOPE_CUCUMBER_GLUE
)
public class CommonContext {

    // === UI ===
    public Integer price;
    public String productName;

    // === API ===
    public Integer statusCode;
    public String responseBody;

    public void reset() {
        price = null;
        productName = null;
        statusCode = null;
        responseBody = null;
    }
}
