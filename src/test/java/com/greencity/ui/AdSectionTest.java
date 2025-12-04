package com.greencity.ui;

import com.greencity.ui.components.AdSectionComponent;
import com.greencity.ui.components.auth.SignInModal;
import com.greencity.ui.testrunners.BaseTestRunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class AdSectionTest extends BaseTestRunner {

    private static final String EXPECTED_TITLE = "Новий спосіб виховати в собі корисні звички";
    private static final String EXPECTED_BUTTON_TEXT = "Почати формувати звичку!";

    private static final String EXPECTED_INFO_TEXT_PARTIAL = "еко-сумку";


    @Test(description = "[#37]Verify Ad section display and Login Form opening for unlogged user")
    public void verifyAdSectionAndLoginFormOpening() {
        homePage.getHeader().changeLanguageToUk();
        AdSectionComponent adSection = homePage.getAdSectionComponent();

        // Verify that the advertising section is visible at the top of the page.
        Assert.assertTrue(adSection.isAdSectionVisible(),
                "Verification failed: The main Ad section is not displayed.");

        //Check that the Title is correct.
        Assert.assertEquals(adSection.getTitleText(), EXPECTED_TITLE,
                "Verification failed: Ad section title is incorrect.");

        // Check that the Informative paragraph about eco-habits is visible.
        String actualInfoText = adSection.getInfoText();
        Assert.assertTrue(actualInfoText.contains(EXPECTED_INFO_TEXT_PARTIAL),
                String.format("Verification failed: Informative text is incorrect or does not contain '%s'. Actual: %s",
                        EXPECTED_INFO_TEXT_PARTIAL, actualInfoText));

        // Check that the Image is displayed.
        Assert.assertTrue(adSection.isImageDisplayed(),
                "Verification failed: Illustration image in the Ad section is not displayed.");

        // Check that the Button is displayed and has correct text.
        Assert.assertTrue(adSection.isButtonDisplayed(),
                "Verification failed: 'Start forming a habit!' button is not displayed.");

        String actualButtonText = adSection.getButtonText();
        Assert.assertTrue(actualButtonText.contains(EXPECTED_BUTTON_TEXT),
                String.format("Verification failed: Button text is incorrect. Actual: %s, Expected: %s",
                        actualButtonText, EXPECTED_BUTTON_TEXT));

        adSection.clickStartFormingHabitButton();

        boolean isLoginFormDisplayed = new SignInModal(driver).isDisplayed();


        Assert.assertTrue(isLoginFormDisplayed,
                "Verification failed: Clicking the button did not open the login form for unlogged user.");
    }

}
