package com.greencity.cucumber.steps;

import com.greencity.ui.pages.CreateEcoNewsPreviewPage;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import static com.greencity.ui.CreateEcoNewsItemPageTest.DATE_FORMATS;
import static com.greencity.ui.CreateEcoNewsItemPageTest.areDatesEqual;

public class CreateNewsPreviewSteps {
    private Hooks hooks;
    private CreateEcoNewsPreviewPage createNewsPreviewPage;

    public CreateNewsPreviewSteps(Hooks hooks) {
        this.hooks = hooks;
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

    @Then("the Create News preview page is opened")
    public void theCreateNewsPreviewPageIsOpened() {
        //createNewsPreviewPage = new CreateEcoNewsPreviewPage(hooks.getDriver());
        createNewsPreviewPage.waitForPageToLoad(50);
    }

    @Then("the news title equals the appropriate input")
    public void theNewsTitleEqualsTheAppropriateInput() {
        Assert.assertEquals(createNewsPreviewPage.getTitle(),CreateNewsPageSteps.itemTitle, "Title should be same");
    }

    @Then("the main text equals the appropriate input")
    public void theMainTextEqualsTheAppropriateInput() {
        Assert.assertEquals(createNewsPreviewPage.getContent(), CreateNewsPageSteps.itemContent, "Content should be same");
    }

    @Then("the date is the current date")
    public void theDateIsTheCurrentDate() {
        Assert.assertTrue(areDatesEqual(createNewsPreviewPage.getDate(), String.valueOf(LocalDate.now())), "Date should be same");
    }

    @Then("the author name equals the user's name")
    public void theAuthorNameEqualsTheUserName() {
        Assert.assertEquals(createNewsPreviewPage.getAuthor().replaceFirst("^(автор |author )", "").trim(), CreateNewsPageSteps.itemAuthor, "Author should be same");
    }

    @Then("the 'Back to editing' button is displayed")
    public void theBackToEditingButtonIsDisplayed() {
        Assert.assertTrue(createNewsPreviewPage.getBackToEditingButton().isDisplayed(), "Back to edit link should be displayed");
    }

    @Then("the 'Back to editing' button has a link")
    public void theBackToEditingButtonHasALink() {
        Assert.assertEquals(createNewsPreviewPage.getLink(), "https://www.greencity.cx.ua/#/greenCity/news/create-news", "Link should be same");
    }

}
