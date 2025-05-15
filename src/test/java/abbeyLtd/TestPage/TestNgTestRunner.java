package abbeyLtd.TestPage;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// cucumber , will only use testng or junit to run cucumber. but cannot use testng or junit features like data provider, assertions etc


@CucumberOptions(features = "src/test/resources/features", glue = "abbeyLtd.stepDefinitions",
        monochrome = true, tags = "@Smoke", plugin = {"html:target/cucumber-reports/cucumber.html"})

public class TestNgTestRunner extends AbstractTestNGCucumberTests {
}
