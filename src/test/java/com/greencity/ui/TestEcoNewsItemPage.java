package com.greencity.ui;

import com.greencity.ui.components.EcoNewsItemComponent;
import com.greencity.ui.pages.EcoNewsItemPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import com.greencity.ui.utils.NewsTag;
import com.greencity.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestEcoNewsItemPage extends TestRunnerWithUser {

    private EcoNewsItemPage ecoNewsItemPage;
    private static final String newsIdentifier = "news/167";

    @BeforeMethod
    public void visitPage() {
        if (testValueProvider.getBaseUIUrl().endsWith("/"))
            driver.get(testValueProvider.getBaseUIUrl() + newsIdentifier);
        else
            driver.get(testValueProvider.getBaseUIUrl() + "/" + newsIdentifier);

        ecoNewsItemPage = new EcoNewsItemPage(driver);
    }

    @Test
    public void testEcoNewsLikes() {
        int oldLikesCount = ecoNewsItemPage.getLikesCount();
        ecoNewsItemPage.clickLike();
        int newLikesCount = ecoNewsItemPage.getLikesCount();
        Assert.assertNotEquals(oldLikesCount, newLikesCount);
    }

    @Test
    public void testEcoNewsTags() {
        var tagsOnPage = ecoNewsItemPage.getTags();

        Assert.assertFalse(tagsOnPage.isEmpty());
        Assert.assertTrue(tagsOnPage.contains(NewsTag.NEWS));
    }

    @Test
    public void testRecommendedNews() {
        EcoNewsItemComponent recommendedNewsItem = ecoNewsItemPage.getRecommendedNewsByOrder(0);
        String expectedTitle = recommendedNewsItem.getTitle();
        var expectedTags = recommendedNewsItem.getTags();

        EcoNewsItemPage recommendedNewsPage = recommendedNewsItem.click();

        Assert.assertEquals(recommendedNewsPage.getTitle(), expectedTitle);
        Assert.assertEquals(recommendedNewsPage.getTags(), expectedTags);
    }

    @Test
    public void testCommentCountLabel() {
        int commentsCountBefore = ecoNewsItemPage.getCommentsCount();
        ecoNewsItemPage = ecoNewsItemPage.addComment(System.currentTimeMillis() + "");

        int commentsCountAfter = ecoNewsItemPage.getCommentsCount();
        Assert.assertEquals(commentsCountAfter, commentsCountBefore + 1);
    }

    @Test(description = "Add 2 new comments, delete one of them, verify successful deletion.")
    public void testAddCommentDeletion() {
        var uniqueTimestamp = System.currentTimeMillis();
        String firstMessage = uniqueTimestamp * 2 + "";
        String secondMessage = uniqueTimestamp * 3 + "";

        ecoNewsItemPage = ecoNewsItemPage
                .addComment(firstMessage)
                .addComment(secondMessage)
                .getFirstComment().deleteComment();

        Assert.assertFalse(ecoNewsItemPage.doesExistCommentByText(secondMessage));
    }

    @Test(description = "[Test Case] 29 - Verify Visual Display of a Saved Comment.")
    public void testCommentComponentElementsVisibility() {
        var comment = ecoNewsItemPage.addComment(System.currentTimeMillis() + "").getFirstComment();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(comment.getCommentBody().isDisplayed(), "Comment text should be displayed");
        softAssert.assertTrue(comment.getAuthorName().isDisplayed(), "Author name should be displayed");
        softAssert.assertTrue(comment.getProfileAvatar().isDisplayed(), "Profile avatar should be displayed");
        softAssert.assertTrue(comment.getCommentDate().isDisplayed(), "Comment date should be displayed");
        softAssert.assertTrue(comment.getEditCommentButton().isDisplayed(), "Edit comment button should be displayed");
        softAssert.assertTrue(comment.getDeleteCommentButton().isDisplayed(), "Delete comment button should be displayed");
        softAssert.assertTrue(comment.getReplyCommentButton().isDisplayed(), "Reply comment button should be displayed");
        softAssert.assertAll();
    }

    @Test(description = "[Test Case] 20 - Verify confirmation modal on delete with Yes and No buttons.")
    public void testCommentDeleteConfirmationModal() {
        String uniqueTimestamp = System.currentTimeMillis() + "";
        ecoNewsItemPage = ecoNewsItemPage.addComment(uniqueTimestamp);

        var comment = ecoNewsItemPage.getFirstComment();
        var modal = comment.clickDeleteCommentButton();
        var modalMessage = modal.getMessage();
        Assert.assertEquals(modalMessage, "Ви дійсно бажаєте видалити коментар?");
        Assert.assertTrue(modal.getConfirmButton().isDisplayed(), "Confirm button should be displayed.");
        Assert.assertTrue(modal.getCancelButton().isDisplayed(), "Cancel button should be displayed.");
        modal.cancel();

        Assert.assertTrue(ecoNewsItemPage.doesExistCommentByText(uniqueTimestamp),
                "The comment should not have been deleted.");

        ecoNewsItemPage = ecoNewsItemPage.getFirstComment().deleteComment();

        Assert.assertFalse(ecoNewsItemPage.doesExistCommentByText(uniqueTimestamp),
                "The comment should have been deleted.");
    }

    @Test(description = "[Test Case] 28 - Add Multiple Comments and Verify Order and Count.")
    public void testAddCommentsOrderAndCount() {
        String firstMessage = "test message - 1";
        String secondMessage = "test message - 2";
        String thirdMessage = "test message - 3";

        int commentsCountBefore = ecoNewsItemPage.getCommentsCount();

        var comments = ecoNewsItemPage.addComment(firstMessage)
                .addComment(secondMessage)
                .addComment(thirdMessage)
                .getComments();

        Assert.assertEquals(comments.get(0).getCommentBodyText(), thirdMessage);
        Assert.assertEquals(comments.get(1).getCommentBodyText(), secondMessage);
        Assert.assertEquals(comments.get(2).getCommentBodyText(), firstMessage);

        Assert.assertEquals(ecoNewsItemPage.getCommentsCount(), commentsCountBefore + 3,
                "Comments count should be: " + commentsCountBefore + 3);
    }

    @Test
    public void testCommentEditBehaviour() {
        ecoNewsItemPage = ecoNewsItemPage.addComment(System.currentTimeMillis() + "");
        String initialCommentText = ecoNewsItemPage.getFirstComment().getCommentBodyText();

        ecoNewsItemPage = ecoNewsItemPage.getFirstComment().editComment(initialCommentText + "edited");

        Assert.assertNotEquals(ecoNewsItemPage.getFirstComment().getCommentBodyText(), initialCommentText);
        Assert.assertTrue(ecoNewsItemPage.getFirstComment().isCommentEdited());
    }

    @Test(description = "[Test Case] 31 - Validation of Comment Input and Comment Button Behavior.")
    public void testCommentInputAndSubmitCommentButtonBehavior() {
        Assert.assertTrue(ecoNewsItemPage.isSubmitCommentButtonDisabled(),
                "Submit comment button should be initially disabled");

        ecoNewsItemPage.getCommentInput().sendKeys("   ");
        Assert.assertTrue(ecoNewsItemPage.isSubmitCommentButtonDisabled(),
                "Submit comment button should be disabled when input contains only spaces");

        ecoNewsItemPage.getCommentInput().sendKeys("q");
        Assert.assertTrue(ecoNewsItemPage.isSubmitCommentButtonEnabled(),
                "Submit comment button should be enabled when input contains valid characters");

        String largeString = TestUtils.randomString(8001);
        ecoNewsItemPage.typeLargeInput(ecoNewsItemPage.getCommentInput(), largeString);
        Assert.assertTrue(ecoNewsItemPage.isSubmitCommentButtonDisabled(),
                "Submit comment button should be disabled when input contains >8000 characters");
        Assert.assertTrue(ecoNewsItemPage.isErrorMessageDisplayed(),
                "Error message should be displayed when input contains >8000 characters");
    }

    @Test(description = "[Test Case] 24 - Verify that changes are not saved without clicking \"Save changes\".")
    public void testCommentCancelChanges() {
        ecoNewsItemPage = ecoNewsItemPage.addComment(System.currentTimeMillis() + "");

        var comment = ecoNewsItemPage.getFirstComment();
        var initialCommentText = comment.getCommentBodyText();

        comment.clickEditCommentButton();
        comment.getEditCommentInput().sendKeys(" edited");

        driver.navigate().refresh();

        Assert.assertEquals(ecoNewsItemPage.getFirstComment().getCommentBodyText(), initialCommentText,
                "The comment should not have been changed when editing interrupted");

        Assert.assertFalse(ecoNewsItemPage.getFirstComment().isCommentEdited(),
                "Comment should not have 'Changed' label");
    }

    @Test(description = "[Test Case] 19 - Verify that deleting a comment removes related replies.")
    public void testCommentRelatedReplies() {
        var uniqueTimestamp = System.currentTimeMillis();
        String firstMessage = uniqueTimestamp * 2 + "";
        String secondMessage = uniqueTimestamp * 3 + "";

        ecoNewsItemPage = ecoNewsItemPage
                .addComment(firstMessage)
                .addComment(secondMessage)
                .getFirstComment().addReply("test reply1")
                .getFirstComment().addReply("test reply2")
                .getFirstComment().addReply("test reply3")
                .getFirstComment().deleteComment();

        Assert.assertFalse(ecoNewsItemPage.doesExistCommentByText(secondMessage),
                "Comment with text" + secondMessage + " should have been deleted");
        Assert.assertFalse(ecoNewsItemPage.getFirstComment().hasReplies(),
                "First comment should not have replies");
    }

}
