package com.greencity.tests;

import com.greencity.components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.mockito.Mockito;

/**
 * Test class for HeaderComponent.
 * Contains basic tests to verify header component functionality.
 * Uses mocked WebDriver to avoid browser dependencies in CI/CD environment.
 */
public class HeaderComponentTest {
    
    @Test
    public void testHeaderComponentInitialization() {
        // Create a mock WebDriver
        WebDriver mockDriver = Mockito.mock(WebDriver.class);
        
        // Initialize HeaderComponent with mock driver
        HeaderComponent headerComponent = new HeaderComponent(mockDriver);
        
        // Verify that HeaderComponent is properly initialized
        Assert.assertNotNull(headerComponent, "HeaderComponent should be initialized");
        Assert.assertNotNull(headerComponent.getDriver(), "WebDriver should be set in HeaderComponent");
        Assert.assertEquals(headerComponent.getDriver(), mockDriver, "WebDriver should match the mock driver");
    }
    
    @Test
    public void testHeaderComponentGetDriver() {
        // Create a mock WebDriver
        WebDriver mockDriver = Mockito.mock(WebDriver.class);
        
        // Initialize HeaderComponent
        HeaderComponent headerComponent = new HeaderComponent(mockDriver);
        
        // Verify getDriver returns the correct WebDriver instance
        Assert.assertNotNull(headerComponent.getDriver(), "getDriver should not return null");
        Assert.assertSame(headerComponent.getDriver(), mockDriver, "getDriver should return the same WebDriver instance");
    }
}
