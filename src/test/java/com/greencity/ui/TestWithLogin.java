package com.greencity.ui;

import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestWithLogin extends TestRunnerWithUser {
    @Test
    public void firstTest() {
        WebElement image = homePage.getHeader().getHeaderLogo();
        Assert.assertTrue(image.isDisplayed(), "The element is not displayed.");
    }
}
