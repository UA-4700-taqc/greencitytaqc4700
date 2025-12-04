package com.greencity.cucumber.steps;

import com.greencity.ui.enums.NewsTag;
import com.greencity.ui.pages.CreateEcoNewsItemPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CreateNewsPageSteps {
    private final Hooks hooks;
    private CreateEcoNewsItemPage createNewsPage;
    public static String itemTitle = randomString(10);
    public static String itemContent = randomString(50);
    public static String itemSource = "https://" + randomString(7);
    public static String itemAuthor;

    public CreateNewsPageSteps(Hooks hooks) {
        this.hooks = hooks;
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

    @Then("the Create News page is loaded")
    public void theCreateEcoNewsPageIsLoaded() {
        createNewsPage = new CreateEcoNewsItemPage(hooks.getDriver());
        createNewsPage.visitPage();
        createNewsPage.waitForPageToLoad(50);
    }

    @When("the user types at least one symbol in the 'Title' field")
    public void theUserTypesAtLeastOneSymbolInTheTitleField() {
        createNewsPage.content.enterTitle(itemTitle);
    }

    @When("the user types at least 20 symbols in the 'Content' field")
    public void theUserTypesAtLeast20SymbolsInTheContentField() {
        createNewsPage.content.enterContent(itemContent);
    }

    @When("the user types a source address starting with https")
    public void theUserTypesASourceAddressStartingWithHttp() {
        createNewsPage.content.enterSource(itemSource);
    }

    @Then("remember the author name")
    public String rememberTheAuthorName() {
        itemAuthor = createNewsPage.meta.getName();
        return  itemAuthor;
    }

    @When("the user clicks the 'Preview' button")
    public void theUserClicksThePreviewButton() {
        createNewsPage.actions.clickPreview();
    }

    @Then("the title field is displayed")
    public void theTitleFieldIsDisplayed() {
        Assert.assertTrue(createNewsPage.content.getTitleInput().isDisplayed(), "Title field should be displayed");
    }

    @Then("five tag buttons are displayed:")
    public void fiveTagButtonsAreDisplayed(@NotNull DataTable dataTable) {
        for (String tagName : dataTable.asList()) {

            // Перетворити рядок у enum (нормалізуючи різні варіанти написання)
            NewsTag tag = NewsTag.valueOf(tagName.toUpperCase());

            WebElement element = createNewsPage.tags.getTagElement(tag);

            Assert.assertTrue(element.isDisplayed(),
                    "Tag button '" + tagName + "' should be displayed");
        }
    }

    @Then("the 'Add image' field is displayed")
    public void theAddImageFieldIsDisplayed() {
        Assert.assertTrue(createNewsPage.getImageRoot().isDisplayed(), "Image upload field should be displayed");
    }

    @Then("the 'Main text' field is displayed")
    public void theMainTextFieldIsDisplayed() {
        Assert.assertTrue(createNewsPage.content.getContentInput().isDisplayed(), "Content input field should be displayed");
    }

    @Then("the 'Author' field is displayed and prefilled and cannot be edited")
    public void theAuthorTextFieldIsDisplayed() {
        Assert.assertFalse(createNewsPage.meta.getName().isEmpty(), "Author name field should be filled in");
        Assert.assertEquals(createNewsPage.meta.getAuthorName().getTagName(), "span", "Author name should not be an editable input");
    }

    @Then("the 'Date' field is displayed and prefilled and cannot be edited")
    public void theDateTextFieldIsDisplayed() {
        Assert.assertFalse(createNewsPage.meta.getDate().isEmpty(), "Creation date field should be filled in");
        Assert.assertEquals(createNewsPage.meta.getAuthorName().getTagName(), "span", "Creation date should not be an editable input");
    }

    @Then("the 'Source' field is displayed")
    public void theSourceTextFieldIsDisplayed() {
        Assert.assertTrue(createNewsPage.content.getSourceInput().isDisplayed(), "Source field should be displayed");
    }

    @Then("the 'Cancel' button is displayed")
    public void theCancelButtonIsDisplayed() {
        Assert.assertTrue(createNewsPage.actions.getExitBtn().isDisplayed(), "Cancel button should be displayed");
    }

    @Then("the 'Preview' button is displayed")
    public void thePreviewButtonIsDisplayed() {
        Assert.assertTrue(createNewsPage.actions.getPreviewBtn().isDisplayed(), "Preview button should be displayed");
    }

    @Then("the 'Publish' button is displayed")
    public void thePublishButtonIsDisplayed() {
        Assert.assertTrue(createNewsPage.actions.getPublishBtn().isDisplayed(), "Publish button should be displayed");
    }
    @When("the user inputs no symbol into the 'Title' field")
    public void theUserInputsNoSymbolIntoTheTitleField() {
        createNewsPage.content.enterTitle(randomString(0));
        createNewsPage.content.enterSource(randomString(0));
    }

    @Then("the title field border is highlighted in red")
    public void theTitleFieldBorderIsHighlightedInRed() {
        Assert.assertTrue(createNewsPage.content.getTitleInput().getCssValue("border-color")
                .contains("255, 0, 0"), "Title input border is not red");
    }

    @Then("the 'Publish' button is disabled")
    public void thePublishButtonIsDisabled() {
        Assert.assertFalse(createNewsPage.actions.getPublishBtn().isEnabled(), "Publish button should be disabled");
    }

    @Then("the character counter shows '0-170'")
    public  void theCharacterCounterShows0() {
        Assert.assertEquals(createNewsPage.content.getTitleSymbolCount().getText(), "0/170", "Some symbols have been already entered");
    }

    @When("the user inputs a 171-character-long string into the 'Title' field")
    public void theUserInputsA171CharacterLongIntoTheTitleField() {
        createNewsPage.content.enterTitle(randomString(171));
    }

    @Then("the entered text is truncated to 170 characters")
    public void theEnteredTextIsTruncatedTo170Characters() {
        Assert.assertEquals(createNewsPage.content.getTitleInput().getAttribute("value").length(), 171, "Text size  isn`t truncated");
    }

    @Then("the character counter is highlighted in red")
    public void theCharacterCounterIsHighlightedInRed() {
        Assert.assertTrue(createNewsPage.content.getTitleSymbolCount().getAttribute("class").contains("warning"), "Text counter isn't red");
    }

    @When("the user inputs 'Test News' into the 'Title' field")
    public void theUserInputsTestNewsIntoTheTitleField() {
        createNewsPage.content.enterTitle("Test News");
    }

    @Then("the character counter shows '9-170'")
    public void theCharacterCounterShows9170() {
        Assert.assertEquals(createNewsPage.content.getTitleInput().getAttribute("value").length(), 9, "Text size isn't counted right");
    }

    @Then("the title field border is not highlighted in red")
    public void theTitleFieldBorderIsNotHighlightedInRed() {
        Assert.assertFalse(createNewsPage.content.getTitleSymbolCount().getAttribute("class").contains("warning"), "Text counter is red");
    }

    @When("the user selects any available tag")
    public void theUserSelectsAnyAvailableTag() {
        createNewsPage.tags.selectTag(NewsTag.getRandomTag());
    }

    @When("the user types at least 20 symbols in the 'Content' field")
    public void theUserTypesAtLeast20SymbolsInTheContent() {
        createNewsPage.content.enterContent(randomString(20));
    }

    @Then("the 'Publish' button is enabled")
    public void thePublishButtonIsEnabled() {
        Assert.assertTrue(createNewsPage.actions.getPublishBtn().isEnabled(), "Publish button is disabled");
    }

}
