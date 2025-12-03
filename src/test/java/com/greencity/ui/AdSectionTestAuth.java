package com.greencity.ui;

import com.greencity.ui.pages.MySpacePage.ProfilePage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AdSectionTestAuth extends TestRunnerWithUser {

    private static final String EXPECTED_HABITS_PAGE_URL_PARTIAL = "/profile";

    private static final String EXPECTED_USER_NAME = "UserTester";

    @Test(description = "[#37]Verify Ad section redirects to My Space (Habits page) for logged in user")
    public void verifyAdSectionRedirectsToMySpace() {

        this.homePage.getHeader().clickHeaderLogo();
        this.homePage = new HomePage(driver);

        ProfilePage profilePage = this.homePage.clickStartHabitButton();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(EXPECTED_HABITS_PAGE_URL_PARTIAL),
                String.format("Redirect failed. Expected URL part: %s, Actual URL: %s",
                        EXPECTED_HABITS_PAGE_URL_PARTIAL, currentUrl));

        String actualName = profilePage.getProfileName();
        Assert.assertEquals(actualName, EXPECTED_USER_NAME,
                "The displayed profile name does not match the expected name of the logged-in user.");

        Assert.assertFalse(profilePage.getProfileName().isEmpty(), "Profile name is empty. Unable to confirm page load.");

    }
}
