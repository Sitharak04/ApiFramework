package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features",
		glue = {"StepDefinitions"},
		plugin = "json:target/jsonReports/cucumber-reports.json"
		//,tags = "@DeletePlaceTag"
		)
public class TestRunner {

}


// Plugin parameter here creates Test result after execution, 
// above command creates json resport in the folder path mentioned
//  maven html reporting plugin uses this generated json report as input and creates html report
// to run with maven html report, run "mvn test verify" 
// cmd to run via cmd--> mvn test -Dcucumber.filter.tags="(@cucumber or @gherkin) and not @salad"
// cmd to enter in jenkins--> test -Dcucumber.filter.tags="(@cucumber or @gherkin) and not @salad"