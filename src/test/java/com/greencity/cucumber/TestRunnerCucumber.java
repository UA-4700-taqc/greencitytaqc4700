package com.greencity.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@CucumberOptions(features = "src/test/resources/features", glue = "com.greencity.cucumber.steps")
public class TestRunnerCucumber extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;
    protected SoftAssert softAssert;

    @BeforeClass
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }


    @Test(description = "Example of BDD suite", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper feature) {
        testNGCucumberRunner.runScenario(pickle.getPickle());
    }

    @Override
    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @Override
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }

    @Before
    public void beforeScenario() {
        softAssert = new SoftAssert();
    }

    @After
    public void afterScenario() {
        softAssert.assertAll();
    }


}