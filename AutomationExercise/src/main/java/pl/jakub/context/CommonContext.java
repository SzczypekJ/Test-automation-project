package pl.jakub.context;

import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.jakub.common.UserCredentials;

@Component
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class CommonContext {

    // === UI ===
    private Integer price;
    private String productName;

    // === API ===
    private Integer statusCode;
    private String responseBody;

    private UserCredentials user;
    private boolean loggedFirstTime = false;

    public void reset() {
        price = null;
        productName = null;
        statusCode = null;
        responseBody = null;
        loggedFirstTime = false;
    }

    // --- UI ---
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    // --- API ---
    public Integer getStatusCode() { return statusCode; }
    public void setStatusCode(Integer statusCode) { this.statusCode = statusCode; }

    public String getResponseBody() { return responseBody; }
    public void setResponseBody(String responseBody) { this.responseBody = responseBody; }

    // --- USER ---
    public UserCredentials getUser() {
        if (user == null) {
            throw new IllegalStateException("CommonContext.user is null. Missing API precondition or scenario setup.");
        }
        return user;
    }

    public void setUser(UserCredentials user) { this.user = user; }

    public boolean hasUser() { return user != null; }

    public boolean isLoggedFirstTime() {
        return loggedFirstTime;
    }

    public void setLoggedFirstTime(boolean loggedFirstTime) {
        this.loggedFirstTime = loggedFirstTime;
    }
}
