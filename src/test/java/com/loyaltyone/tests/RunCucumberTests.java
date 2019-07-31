package com.loyaltyone.tests;

import com.cucumber.listener.Reporter;
import com.loyaltyone.utility.StarWarsPropertyReader;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/com/loyaltyone/features/"},
glue = {"classpath:com/loyaltyone/steps/"}, 
plugin = {"com.cucumber.listener.ExtentCucumberFormatter:results/RunResults.html"},
monochrome =  true)
public class RunCucumberTests{

	@AfterClass
	public static void teardown() {
		StarWarsPropertyReader reader = new StarWarsPropertyReader();
		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
		Reporter.setSystemInfo("Endpoint", reader.getUrl());
		Reporter.setSystemInfo("Test Suite", "StarWars Rest API");
		
	}
}
