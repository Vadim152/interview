package org.example;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameters({
        @ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "org.example.steps"),
        @ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, summary")
})
public class RunCucumberTest {
    static {
        System.setProperty(Constants.FILTER_TAGS_PROPERTY_NAME,
                System.getProperty(Constants.FILTER_TAGS_PROPERTY_NAME, "@demo"));
    }
}
