package cucumberTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty" },
        features = "src/test/resources"
        ,glue={"steps"}, tags ="@smoke1"
)

public class TestRunner {

}