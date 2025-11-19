package com.greencity.ui;

import com.greencity.ui.components.header.HeaderComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderComponentTest extends BaseTestRunner {

    @BeforeMethod
    public void ensurePageIsLoaded() {
        driver.navigate().refresh();
        homePage.waitForPageToLoad(10);
    }

    @Test(description = "Verify header logo is displayed")
    public void testHeaderLogoIsDisplayed() {
        HeaderComponent header = homePage.getHeader();
        WebElement logo = header.getHeaderLogo();
        Assert.assertTrue(logo.isDisplayed(), "Header logo should be displayed");
    }

    @Test(description = "Verify header container is displayed")
    public void testHeaderContainerIsDisplayed() {
        HeaderComponent header = homePage.getHeader();
        WebElement container = header.getHeaderContainer();
        Assert.assertTrue(container.isDisplayed(), "Header container should be displayed");
    }

    @Test(description = "Verify navigation menu is displayed")
    public void testNavigationMenuIsDisplayed() {
        HeaderComponent header = homePage.getHeader();
        WebElement navMenu = header.getNavigationMenu();
        Assert.assertTrue(navMenu.isDisplayed(), "Navigation menu should be displayed");
    }

    @Test(description = "Verify all navigation links are displayed")
    public void testAllNavigationLinksAreDisplayed() {
        HeaderComponent header = homePage.getHeader();
        Assert.assertTrue(header.getEcoNewsLink().isDisplayed(), "Eco news link should be displayed");
        Assert.assertTrue(header.getEventsLink().isDisplayed(), "Events link should be displayed");
        Assert.assertTrue(header.getPlacesLink().isDisplayed(), "Places link should be displayed");
        Assert.assertTrue(header.getAboutUsLink().isDisplayed(), "About us link should be displayed");
        Assert.assertTrue(header.getMySpaceLink().isDisplayed(), "My space link should be displayed");
        Assert.assertTrue(header.getUbsCourierLink().isDisplayed(), "UBS courier link should be displayed");
    }

    @Test(description = "Verify search icon is displayed")
    public void testSearchIconIsDisplayed() {
        HeaderComponent header = homePage.getHeader();
        WebElement searchIcon = header.getSearchIcon();
        Assert.assertTrue(searchIcon.isDisplayed(), "Search icon should be displayed");
    }

    @Test(description = "Verify language switcher is displayed")
    public void testLanguageSwitcherIsDisplayed() {
        HeaderComponent header = homePage.getHeader();
        WebElement langSwitcher = header.getLanguageSwitcher();
        Assert.assertTrue(langSwitcher.isDisplayed(), "Language switcher should be displayed");
    }

    @Test(description = "Verify sign in button is displayed")
    public void testSignInButtonIsDisplayed() {
        HeaderComponent header = homePage.getHeader();
        WebElement signInBtn = header.getSignInButton();
        Assert.assertTrue(signInBtn.isDisplayed(), "Sign in button should be displayed");
    }

    @Test(description = "Verify sign up button is displayed")
    public void testSignUpButtonIsDisplayed() {
        HeaderComponent header = homePage.getHeader();
        WebElement signUpBtn = header.getSignUpButton();
        Assert.assertTrue(signUpBtn.isDisplayed(), "Sign up button should be displayed");
    }

    @Test(description = "Verify header logo is clickable")
    public void testHeaderLogoIsClickable() {
        HeaderComponent header = homePage.getHeader();
        WebElement logo = header.getHeaderLogo();
        Assert.assertTrue(logo.isEnabled(), "Header logo should be clickable");
        logo.click();
    }

    @Test(description = "Verify navigation links count")
    public void testNavigationLinksCount() {
        HeaderComponent header = homePage.getHeader();
        int expectedLinksCount = 6;
        int actualLinksCount = header.getNavigationLinks().size();
        Assert.assertEquals(actualLinksCount, expectedLinksCount, 
            "Expected " + expectedLinksCount + " navigation links, but found " + actualLinksCount);
    }
}
