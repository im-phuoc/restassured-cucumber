package hooks;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        TestContext.getInstance();
        System.out.println(scenario.getName() + " - " + Thread.currentThread().getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        TestContext.getInstance().remove();
        System.out.println(scenario.getName() + " - " + Thread.currentThread().getName());
        TestContext.getInstance().remove();
    }
}
