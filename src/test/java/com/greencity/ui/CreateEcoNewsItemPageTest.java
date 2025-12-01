package com.greencity.ui;

import com.greencity.ui.enums.NewsTag;
import com.greencity.ui.pages.CreateEcoNewsItemPage;
import com.greencity.ui.pages.CreateEcoNewsPreviewPage;
import com.greencity.ui.pages.EcoNewsItemPage;
import com.greencity.ui.pages.newspage.NewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class CreateEcoNewsItemPageTest extends TestRunnerWithUser {


    private static final String[] DATE_FORMATS = {"MMM dd, yyyy",        // Nov 26, 2025
            "LLL dd, yyyy 'р.'",   // лист. 26, 2025 р.
            "dd.MM.yyyy",          // 26.11.2025
            "yyyy-MM-dd"           // 2025-11-26
    };
    private CreateEcoNewsItemPage createNewsPage;

    public static String randomString(int n) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(n);
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < n; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static boolean areDatesEqual(String date1, String date2) {
        try {
            Date d1 = parseDate(date1);
            Date d2 = parseDate(date2);
            if (d1 == null || d2 == null) return false;
            return d1.equals(d2);
        } catch (ParseException e) {
            return false;
        }
    }

    private static Date parseDate(String date) throws ParseException {
        for (String format : DATE_FORMATS) {
            try {
                Locale locale = format.contains("LLL") ? new Locale("uk") : Locale.ENGLISH;
                return new SimpleDateFormat(format, locale).parse(date);
            } catch (ParseException ignored) {
            }
        }
        return null;
    }

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

    @Test(description = "Verify the validation of the \"Title\" field (mandatory, maximum 170 characters) and that the \"Publish\" button remains disabled until both Title and Main Text (Content) fields are filled and at least one tag is chosen")
    public void TitleFieldValidation() {
        createNewsPage.content.enterTitle(randomString(0));
        createNewsPage.content.enterSource(randomString(0));
        Assert.assertTrue(createNewsPage.content.getTitleInput().getCssValue("border-color").contains("255, 0, 0"), "Title input border is not red");
        Assert.assertFalse(createNewsPage.actions.getPublishBtn().isEnabled(), "Publish button should be disabled");
        Assert.assertEquals(createNewsPage.content.getTitleSymbolCount().getText(), "0/170", "Some symbols have been already entered");
        createNewsPage.content.enterTitle(randomString(171));
        Assert.assertEquals(createNewsPage.content.getTitleInput().getAttribute("value").length(), 171, "Text size  isn`t truncated");
        Assert.assertTrue(createNewsPage.content.getTitleSymbolCount().getAttribute("class").contains("warning"), "Text counter isn't red");
        createNewsPage.content.enterTitle("Test News");
        Assert.assertEquals(createNewsPage.content.getTitleInput().getAttribute("value").length(), 9, "Text size isn't counted right");
        Assert.assertFalse(createNewsPage.content.getTitleSymbolCount().getAttribute("class").contains("warning"), "Text counter is red");
        Assert.assertFalse(createNewsPage.actions.getPublishBtn().isEnabled(), "Publish button should be disabled");
        createNewsPage.tags.selectTag(NewsTag.getRandomTag());
        createNewsPage.content.enterContent(randomString(65));
        Assert.assertTrue(createNewsPage.actions.getPublishBtn().isEnabled(), "Publish button is disabled");

    }

    @Test(description = "Verify that the 'Create News' form displays all the necessary fields in the correct order")
    public void CheckingCreateNewsFormDisplayAllFields() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createNewsPage.content.getTitleInput().isDisplayed(), "Title field should be displayed");
        softAssert.assertTrue(createNewsPage.tags.getTagNewsBtn().isDisplayed(), "News tag button should be displayed");
        softAssert.assertTrue(createNewsPage.tags.getTagEventsBtn().isDisplayed(), "Events tag button should be displayed");
        softAssert.assertTrue(createNewsPage.tags.getTagEducationBtn().isDisplayed(), "Education tag button should be displayed");
        softAssert.assertTrue(createNewsPage.tags.getTagInitiativesBtn().isDisplayed(), "Initiatives tag button should be displayed");
        softAssert.assertTrue(createNewsPage.tags.getTagAdvertisingBtn().isDisplayed(), "Advertising tag button should be displayed");
        softAssert.assertTrue(createNewsPage.getImageRoot().isDisplayed(), "Image upload field should be displayed");
        softAssert.assertTrue(createNewsPage.content.getContentInput().isDisplayed(), "Content input field should be displayed");
        softAssert.assertFalse(createNewsPage.meta.getName().isEmpty(), "Author name field should be filled in");
        softAssert.assertEquals(createNewsPage.meta.getAuthorName().getTagName(), "span", "Author name should not be an editable input");
        softAssert.assertFalse(createNewsPage.meta.getDate().isEmpty(), "Creation date field should be filled in");
        softAssert.assertEquals(createNewsPage.meta.getAuthorName().getTagName(), "span", "Creation date should not be an editable input");
        softAssert.assertTrue(createNewsPage.content.getSourceInput().isDisplayed(), "Source field should be displayed");
        softAssert.assertTrue(createNewsPage.actions.getExitBtn().isDisplayed(), "Cancel button should be displayed");
        softAssert.assertTrue(createNewsPage.actions.getReviewBtn().isDisplayed(), "Review button should be displayed");
        softAssert.assertTrue(createNewsPage.actions.getPublishBtn().isDisplayed(), "Publish button should be displayed");
        softAssert.assertAll();
    }

    @Test(description = "Verify that the user can preview news content after entering valid data and that the preview matches the input")
    public void BasicPreviewFunctionality() {
        String itemTitle = randomString(10);
        String itemContent = randomString(50);
        String itemSource = "https://" + randomString(7);

        createNewsPage.content.enterTitle(itemTitle);
        createNewsPage.content.enterContent(itemContent);
        createNewsPage.content.enterSource(itemSource);
        String itemAuthor = createNewsPage.meta.getName();
        String itemDate = createNewsPage.meta.getDate();
        createNewsPage.actions.clickReview();
        CreateEcoNewsPreviewPage createNewsPagePreview = new CreateEcoNewsPreviewPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createNewsPagePreview.getTitle(), itemTitle, "Title should be same");
        softAssert.assertTrue(areDatesEqual(createNewsPagePreview.getDate(), itemDate), "Date should be same");
        softAssert.assertEquals(createNewsPagePreview.getAuthor().replaceFirst("^(автор |author )", "").trim(), itemAuthor, "Author should be same");
        softAssert.assertEquals(createNewsPagePreview.getContent(), itemContent, "Content should be same");
        softAssert.assertEquals(createNewsPagePreview.getSource(), itemSource, "Source should be same");
        softAssert.assertTrue(createNewsPagePreview.getBackToEditingButton().isDisplayed(), "Back to edit link should be displayed");
        softAssert.assertEquals(createNewsPagePreview.getLink(), "https://www.greencity.cx.ua/#/greenCity/news/create-news", "Link should be same");
        softAssert.assertAll();


    }

    @Test(description = "Verify the validation of the 'Source' field")
    public void SourceFieldValidation() {
        String itemTitle = randomString(10);
        String itemContent = randomString(50);
        String itemSource = "https://" + randomString(7);

        createNewsPage.content.enterTitle(itemTitle);
        createNewsPage.content.enterContent(itemContent);
        createNewsPage.tags.selectTag(NewsTag.getRandomTag());
        createNewsPage.actions.clickPublish();

        String message = createNewsPage.waitForSuccessMessage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(message.toLowerCase().contains("success"),"Success message should appear after publish");



        softAssert.assertAll();
    }

}
