package com.greencity.cucumber.steps;

import com.greencity.ui.components.CommentComponent;
import com.greencity.ui.components.InformationModal;
import com.greencity.ui.pages.EcoNewsItemPage;
import com.greencity.utils.TestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class EcoNewsItemPageSteps {

    private final Hooks hooks;
    private EcoNewsItemPage ecoNewsItemPage;
    private static final String newsIdentifier = "news/167";
    int initialCommentsCount;
    CommentComponent comment;
    String commentText;
    InformationModal modal;

    public EcoNewsItemPageSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    @When("the user is on the Eco News Item page")
    public void the_user_is_on_the_Eco_News_item_page() {
        if (hooks.getTestValueProvider().getBaseUIUrl().endsWith("/"))
            hooks.getDriver().get(hooks.getTestValueProvider().getBaseUIUrl() + newsIdentifier);
        else
            hooks.getDriver().get(hooks.getTestValueProvider().getBaseUIUrl() + "/" + newsIdentifier);

        ecoNewsItemPage = new EcoNewsItemPage(hooks.getDriver());
        initialCommentsCount = ecoNewsItemPage.getCommentsCount();
    }

    @Given("the user has at least one comment")
    public void the_user_has_one_comment() {
        initialCommentsCount = ecoNewsItemPage.getCommentsCount();
        ecoNewsItemPage = ecoNewsItemPage.addComment(System.currentTimeMillis() + "");
        commentText = ecoNewsItemPage.getFirstComment().getCommentBodyText();
        comment = ecoNewsItemPage.getFirstComment();
    }

    @When("the user leaves the comment field empty")
    public void the_user_leaves_the_comment_field_empty() {
        ecoNewsItemPage.getCommentInput().click();
        ecoNewsItemPage.getCommentInput().sendKeys("");
    }

    @When("the user enters only spaces into the comment field")
    public void the_user_enters_only_spaces_into_the_comment_field() {
        ecoNewsItemPage.getCommentInput().sendKeys("   ");
    }

    @When("the user enters a valid character into the comment field")
    public void the_user_enters_a_valid_character_into_the_comment_field() {
        ecoNewsItemPage.getCommentInput().sendKeys("q");
    }

    @When("the user enters more than 8000 characters into the comment field")
    public void the_user_enters_more_than_8000_characters_into_the_comment_field() {
        String largeString = TestUtils.randomString(8001);
        ecoNewsItemPage.typeLargeInput(ecoNewsItemPage.getCommentInput(), largeString);
    }

    @Then("the submit comment button should be disabled")
    public void the_submit_comment_button_should_be_disabled() {
        Assert.assertTrue(ecoNewsItemPage.isSubmitCommentButtonDisabled(),
                "Submit comment button should be disabled");
    }

    @Then("the submit comment button should be enabled")
    public void the_submit_comment_button_should_be_enabled() {
        Assert.assertTrue(ecoNewsItemPage.isSubmitCommentButtonEnabled(),
                "Submit comment button should be enabled");
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        Assert.assertTrue(ecoNewsItemPage.isErrorMessageDisplayed(),
                "Error message should be displayed");
    }

    @When("the user adds three comments")
    public void the_user_adds_three_comments() {
        ecoNewsItemPage = ecoNewsItemPage
                .addComment("test message - 1")
                .addComment("test message - 2")
                .addComment("test message - 3");
    }

    @Then("the comments should appear in reverse chronological order")
    public void the_comments_should_appear_in_reverse_chronological_order() {
        var comments = ecoNewsItemPage.getComments();

        Assert.assertEquals(comments.get(0).getCommentBodyText(), "test message - 3");
        Assert.assertEquals(comments.get(1).getCommentBodyText(), "test message - 2");
        Assert.assertEquals(comments.get(2).getCommentBodyText(), "test message - 1");
    }

    @Then("the total number of comments should increase by {int}")
    public void the_total_number_of_comments_should_increase_by_number(int number) {
        Assert.assertEquals(
                ecoNewsItemPage.getCommentsCount(),
                initialCommentsCount + number
        );
    }

    @When("the user clicks {string} icon on the comment")
    public void the_user_clicks_action_button_on_the_comment(String action) {
        switch (action.trim().toLowerCase()) {
            case "delete" -> {
                ecoNewsItemPage.getFirstComment().clickDeleteCommentButton();
                modal = new InformationModal(hooks.getDriver());
            }
            case "edit" -> ecoNewsItemPage.getFirstComment().clickEditCommentButton();
            case "reply" -> ecoNewsItemPage.getFirstComment().clickReplyButton();
            default -> throw new IllegalArgumentException("Invalid action: " + action);
        }
    }

    @Then("a confirmation modal should appear")
    public void a_confirmation_modal_should_appear() {
        Assert.assertTrue(modal.getConfirmButton().isDisplayed(), "Confirm button should be displayed.");
        Assert.assertTrue(modal.getCancelButton().isDisplayed(), "Cancel button should be displayed.");
    }

    @When("the user clicks {string} on the confirmation modal")
    public void the_user_clicks_action_on_the_confirmation_modal(String action) {
        switch (action.trim().toLowerCase()) {
            case "yes" -> modal.confirm();
            case "no" -> modal.cancel();
            default -> throw new IllegalArgumentException("Invalid action: " + action);
        }
    }

    @Then("the comment should be visible")
    public void the_comment_should_be_visible() {
        Assert.assertTrue(ecoNewsItemPage.doesExistCommentByText(commentText),
                "The comment should not have been deleted.");
    }

    @Then("the comment should be deleted")
    public void the_comment_should_be_deleted() {
        ecoNewsItemPage.getFirstComment().waitForCommentDeletion();
        Assert.assertFalse(ecoNewsItemPage.doesExistCommentByText(commentText),
                "The comment should have been deleted.");
    }

    @When("the user modifies the comment text without saving")
    public void the_user_modifies_the_comment_text_without_saving() {
        comment.getEditCommentInput().sendKeys(" edited");
    }

    @When("the user refreshes the page")
    public void the_user_refreshes_the_page() {
        ecoNewsItemPage = ecoNewsItemPage.reloadPage();
    }

    @Then("the comment should remain unchanged")
    public void the_comment_should_remain_unchanged() {
        Assert.assertEquals(ecoNewsItemPage.getFirstComment().getCommentBodyText(), commentText,
                "The comment should not have been changed");
    }

    @Then("the comment should not have an edit label")
    public void the_comment_should_not_have_an_edit_label() {
        Assert.assertFalse(ecoNewsItemPage.getFirstComment().isCommentEdited(),
                "Comment should not have 'Changed' label");
    }

    @Then("all replies under the comment should also be deleted")
    public void all_replies_under_the_comment_should_be_deleted() {
        Assert.assertFalse(ecoNewsItemPage.getFirstComment().hasReplies(),
                "First comment should not have replies");
    }

    @Then("the comment text should be visible")
    public void the_comment_text_should_be_visible() {
        Assert.assertTrue(comment.getCommentBody().isDisplayed(), "Comment text should be displayed");
    }

    @Then("the author name should be visible")
    public void the_author_name_should_be_visible() {
        Assert.assertTrue(comment.getAuthorName().isDisplayed(), "Author name should be displayed");
    }

    @Then("the profile avatar should be visible")
    public void the_profile_avatar_should_be_visible() {
        Assert.assertTrue(comment.getProfileAvatar().isDisplayed(), "Profile avatar should be displayed");
    }

    @Then("the comment date should be visible")
    public void the_comment_date_should_be_visible() {
        Assert.assertTrue(comment.getCommentDate().isDisplayed(), "Comment date should be displayed");
    }

    @Then("the edit comment button should be visible")
    public void the_edit_comment_button_should_be_visible() {
        Assert.assertTrue(comment.getEditCommentButton().isDisplayed(), "Edit comment button should be displayed");

    }

    @Then("the delete comment button should be visible")
    public void the_delete_comment_button_should_be_visible() {
        Assert.assertTrue(comment.getDeleteCommentButton().isDisplayed(), "Delete comment button should be displayed");
    }

    @Then("the reply comment button should be visible")
    public void the_reply_comment_button_should_be_visible() {
        Assert.assertTrue(comment.getReplyCommentButton().isDisplayed(), "Reply comment button should be displayed");
    }

}