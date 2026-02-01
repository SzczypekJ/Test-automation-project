package pl.jakub.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import pl.jakub.context.CommonContext;

public class ContextHook {

    private final CommonContext ctx;

    public ContextHook(CommonContext ctx) {
        this.ctx = ctx;
    }

    @Before(order = 0)
    public void beforeScenario() {
        ctx.reset();
    }

    @After(order = 999)
    public void afterScenario() {
        ctx.reset();
    }
}
