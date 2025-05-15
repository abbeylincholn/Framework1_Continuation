package abbeyLtd.TestPage;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features = "src/test/resources/features/SubmitOrder.feature", glue = "abbeyLtd.stepDefinitions",
        monochrome = true, plugin = {"html:target/cucumber-reports/cucumber.html"})

public class TestNgTestRunner extends AbstractTestNGCucumberTests {
}
