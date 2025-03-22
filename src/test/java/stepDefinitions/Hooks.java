package stepDefinitions;

import io.cucumber.java.After;
import utils.TestContext;

public class Hooks {
    @After
    public void afterScenario() {
        TestContext.getInstance().clear();
    }
}
