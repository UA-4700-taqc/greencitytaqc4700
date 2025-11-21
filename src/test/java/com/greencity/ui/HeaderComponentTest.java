package com.greencity.ui;

import com.greencity.ui.components.header.HeaderComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HeaderComponentTest extends BaseTestRunner {

    private static final int PAGE_LOAD_TIMEOUTS = 10;
    private static final int EXPECTED_NAVIGATION_LINKS_COUNT = 6;
    private HeaderComponent header;

    @BeforeMethod
    public void ensurePageIsLoaded() {
        driver.navigate().refresh();
        homePage.waitForPageToLoad(PAGE_LOAD_TIMEOUTS);
        header = homePage.getHeader();
    }

    @Test(description = "Verify header logo is displayed")
    public void testHeaderLogoIsDisplayed() {
        WebElement logo = header.getHeaderLogo();
        Assert.assertTrue(logo.isDisplayed(), "Header logo should be displayed");
    }

    @Test(description = "Verify header container is displayed")
    public void testHeaderContainerIsDisplayed() {
        WebElement container = header.getHeaderContainer();
        Assert.assertTrue(container.isDisplayed(), "Header container should be displayed");
    }

    @Test(description = "Verify navigation menu is displayed")
    public void testNavigationMenuIsDisplayed() {
        WebElement navMenu = header.getNavigationMenu();
        Assert.assertTrue(navMenu.isDisplayed(), "Navigation menu should be displayed");
    }

    @Test(description = "Verify all navigation links are displayed")
    public void testAllNavigationLinksAreDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(header.getEcoNewsLink().isDisplayed(), "Eco news link should be displayed");
        softAssert.assertTrue(header.getEventsLink().isDisplayed(), "Events link should be displayed");
        softAssert.assertTrue(header.getPlacesLink().isDisplayed(), "Places link should be displayed");
        softAssert.assertTrue(header.getAboutUsLink().isDisplayed(), "About us link should be displayed");
        softAssert.assertTrue(header.getMySpaceLink().isDisplayed(), "My space link should be displayed");
        softAssert.assertTrue(header.getUbsCourierLink().isDisplayed(), "UBS courier link should be displayed");
        softAssert.assertAll();
    }

    @Test(description = "Verify search icon is displayed")
    public void testSearchIconIsDisplayed() {
        WebElement searchIcon = header.getSearchIcon();
        Assert.assertTrue(searchIcon.isDisplayed(), "Search icon should be displayed");
    }

    @Test(description = "Verify language switcher is displayed")
    public void testLanguageSwitcherIsDisplayed() {
        WebElement langSwitcher = header.getLanguageSwitcher();
        Assert.assertTrue(langSwitcher.isDisplayed(), "Language switcher should be displayed");
    }

    @Test(description = "Verify sign in button is displayed")
    public void testSignInButtonIsDisplayed() {
        WebElement signInBtn = header.getSignInButton();
        Assert.assertTrue(signInBtn.isDisplayed(), "Sign in button should be displayed");
    }

    @Test(description = "Verify sign up button is displayed")
    public void testSignUpButtonIsDisplayed() {
        WebElement signUpBtn = header.getSignUpButton();
        Assert.assertTrue(signUpBtn.isDisplayed(), "Sign up button should be displayed");
    }

    @Test(description = "Verify header logo is clickable")
    public void testHeaderLogoClick() {
        header.clickHeaderLogo();
    }

    @Test(description = "Verify navigation links count")
    public void testNavigationLinksCount() {
        int actualLinksCount = header.getNavigationLinks().size();
        Assert.assertEquals(actualLinksCount, EXPECTED_NAVIGATION_LINKS_COUNT, 
            "Expected " + EXPECTED_NAVIGATION_LINKS_COUNT + " navigation links, but found " + actualLinksCount);
    }

    @Test(description = "Verify navigation link clicks")
    public void testNavigationLinkClicks() {
        String[] linkNames = {"eco news", "events", "places"};
        for (String linkName : linkNames) {
            header.clickNavigationLink(linkName);
            driver.navigate().back();
            ensurePageIsLoaded();
        }
    }
}
