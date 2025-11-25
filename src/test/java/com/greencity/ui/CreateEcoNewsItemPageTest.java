package com.greencity.ui;

import com.greencity.ui.enums.NewsTag;
import com.greencity.ui.pages.CreateEcoNewsItemPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateEcoNewsItemPageTest extends TestRunnerWithUser {


    private CreateEcoNewsItemPage createNewsPage;

    @BeforeMethod
    public void openPageBeforeEachTest() {
        createNewsPage = new CreateEcoNewsItemPage(driver);
        createNewsPage.visitPage();
        createNewsPage.waitForPageToLoad(50);
    }


    @Test
    public void testHeaderSection() {
        String expectedTitleHeader = "Створити новину";
        String expectedTitleDescription = "Будь ласка, надайте якомога більше деталей - час та місце події, мета зборів тощо. Ви можете повернутись та оновити новину в будь-який час після публікації.";

        String actualTitleHeader = createNewsPage.header.getHeader();
        String actualTitleDescription = createNewsPage.header.getDescription();

        Assert.assertEquals(actualTitleHeader, expectedTitleHeader, "Title of the page doesn't match the expected text");
        Assert.assertEquals(actualTitleDescription, expectedTitleDescription, "Description of the page doesn't match the expected text");
    }

    @Test (description = "Verify the validation of the \"Title\" field (mandatory, maximum 170 characters) and that the \"Publish\" button remains disabled until both Title and Main Text (Content) fields are filled and at least one tag is choosed")
    public void TitleFieldValidation() {
        createNewsPage.content.enterTitle(randomString(0));
        createNewsPage.content.enterSource(randomString(0));
        Assert.assertTrue(createNewsPage.content.getTitleInput().getCssValue("border-color").contains("255, 0, 0"), "Title input border is not red");
        Assert.assertFalse(createNewsPage.actions.getPublishBtn().isEnabled(), "Publish button should be disabled");
        Assert.assertEquals(createNewsPage.content.getTitleSymbolCount().getText(),"0/170", "Some symbols has been already entered");
        createNewsPage.content.enterTitle(randomString(171));
        Assert.assertEquals(createNewsPage.content.getTitleInput().getAttribute("value").length(),171, "Text size  isn`t truncated");
        Assert.assertTrue(createNewsPage.content.getTitleSymbolCount().getAttribute("class").contains("warning"), "Text counter isn't red");
        createNewsPage.content.enterTitle("Test News");
        Assert.assertEquals(createNewsPage.content.getTitleInput().getAttribute("value").length(),9, "Text size  isn`t counted right");
        Assert.assertFalse(createNewsPage.content.getTitleSymbolCount().getAttribute("class").contains("warning"), "Text counter is red");
        Assert.assertFalse(createNewsPage.actions.getPublishBtn().isEnabled(), "Publish button should be disabled");
        createNewsPage.tags.selectTag(NewsTag.getRandomTag());
        createNewsPage.content.enterContent(randomString(65));
        Assert.assertTrue(createNewsPage.actions.getPublishBtn().isEnabled(), "Publish button is disabled");

    }

    @Test (description = "Verify that the 'Create News' form displays all the necessary fields in the correct order")
    public void CheckingCreateNewsFormDisplayAllFields() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createNewsPage.content.getTitleInput().isDisplayed(), "Title field should be displayed");
        softAssert.assertTrue(createNewsPage.tags.getTagNewsBtn().isDisplayed(), "News tag button should be displayed");
        softAssert.assertTrue(createNewsPage.tags.getTagEventsBtn().isDisplayed(), "Events tag button should be displayed");
        softAssert.assertTrue(createNewsPage.tags.getTagEducationBtn().isDisplayed(), "Education tag button should be displayed");
        softAssert.assertTrue(createNewsPage.tags.getTagInitiativesBtn().isDisplayed(), "Initiatives tag button should be displayed");
        softAssert.assertTrue(createNewsPage.tags.getTagAdvertisingBtn().isDisplayed(), "Advertising tag button should be displayed");
//        softAssert.assertTrue(createNewsPage.image.getUploadInput().isDisplayed(), "Image upload field should be displayed");
        softAssert.assertTrue(createNewsPage.content.getContentInput().isDisplayed(), "Content input field should be displayed");
//        softAssert.assertFalse(createNewsPage.meta.getAuthorName().isEmpty(), "Author name field should be displayed");
//        softAssert.assertFalse(createNewsPage.meta.getCreationDate().isEmpty(), "Creation date field should be displayed");
        softAssert.assertTrue(createNewsPage.content.getSourceInput().isDisplayed(), "Source field should be displayed");
        softAssert.assertTrue(createNewsPage.actions.getExitBtn().isDisplayed(), "Cancel button should be displayed");
        softAssert.assertTrue(createNewsPage.actions.getReviewBtn().isDisplayed(), "Review button should be displayed");
        softAssert.assertTrue(createNewsPage.actions.getPublishBtn().isDisplayed(), "Publish button should be displayed");


        softAssert.assertAll();
    }

    public static String randomString(int n) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(n);
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < n; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
