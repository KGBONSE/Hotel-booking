//package steps;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(features = "src/test/resources")
//public class TestRunner {
//}


package steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty" },
        features = "src/test/resources"
        ,glue={"steps"}, tags ="@smoke"
)

public class TestRunner {

}