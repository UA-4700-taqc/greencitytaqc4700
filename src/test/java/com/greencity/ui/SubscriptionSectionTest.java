package com.greencity.ui;

import com.greencity.ui.components.SubscriptionSectionComponent;
import com.greencity.ui.pages.homepage.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubscriptionSectionTest extends BaseTestRunner {

    @Test(description = "Verify section visibility and static elements")
    public void verifySubscriptionSectionVisibilityAndElements() {

        SubscriptionSectionComponent subscriptionSection = new SubscriptionSectionComponent(driver);
        subscriptionSection.scrollToSection();

        Assert.assertTrue(subscriptionSection.isSectionDisplayed(), "The subscription section should be visible after scrolling.");

        String expectedTitle = "Receive interesting news";
        Assert.assertEquals(subscriptionSection.getSectionTitleText(), expectedTitle, "The text of the subscription section heading does not match what is expected.");

        Assert.assertTrue(subscriptionSection.getQrCodeImage(), "The QR code for the mobile version should be displayed.");

        String expectedInfoText = "Subscribe for our newsletter and always be up to date with news and updates";
        Assert.assertEquals(subscriptionSection.getInfoText(), expectedInfoText, "The informational text of the section does not meet expectations.");

        String expectedPlaceholder = "Enter your email";
        Assert.assertEquals(subscriptionSection.getEmailInputPlaceholder(), expectedPlaceholder, "The email field placeholder does not match the expected one.");

        Assert.assertTrue(subscriptionSection.getSubscribeButton().isDisplayed(), "The subscribe button should be displayed.");
    }
}
