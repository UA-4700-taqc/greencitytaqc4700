package com.greencity.ui;

import com.greencity.ui.components.CommentComponent;
import com.greencity.ui.components.EcoNewsItemComponent;
import com.greencity.ui.pages.EcoNewsItemPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import com.greencity.ui.utils.NewsTag;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestEcoNewsItemPage extends TestRunnerWithUser {

    private EcoNewsItemPage ecoNewsItemPage;
    private static final String newsIdentifier = "news/149";

    @BeforeMethod
    public void visitPage() {
        if (testValueProvider.getBaseUIUrl().endsWith("/"))
            driver.get(testValueProvider.getBaseUIUrl() + newsIdentifier);
        else
            driver.get(testValueProvider.getBaseUIUrl() + "/" + newsIdentifier);

        ecoNewsItemPage = new EcoNewsItemPage(driver);
    }

    @Test
    public void testSubmitCommentButton() {
        String uniqueTimestamp = System.currentTimeMillis() + "";

        Assert.assertTrue(ecoNewsItemPage.isSubmitCommentButtonDisabled());

        ecoNewsItemPage.getCommentInput().sendKeys(uniqueTimestamp);
        ecoNewsItemPage.waitSubmitCommentButtonEnabled();
        Assert.assertTrue(ecoNewsItemPage.isSubmitCommentButtonEnabled());

        ecoNewsItemPage.getCommentInput().clear();
        ecoNewsItemPage.getCommentInput().sendKeys(" ");
        ecoNewsItemPage.waitSubmitCommentButtonDisabled();
        Assert.assertTrue(ecoNewsItemPage.isSubmitCommentButtonDisabled());

        ecoNewsItemPage = ecoNewsItemPage.addComment(uniqueTimestamp);
        var firstComment = ecoNewsItemPage.getFirstComment();
        Assert.assertEquals(firstComment.getCommentBodyText(), uniqueTimestamp);
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
        String uniqueTimestamp = System.currentTimeMillis() + "";
        int commentsCountBefore = ecoNewsItemPage.getCommentsCount();
        ecoNewsItemPage = ecoNewsItemPage.addComment(uniqueTimestamp);

        var firstComment = ecoNewsItemPage.getFirstComment();

        Assert.assertEquals(firstComment.getCommentBodyText(), uniqueTimestamp);

        int commentsCountAfter = ecoNewsItemPage.getCommentsCount();
        Assert.assertEquals(commentsCountAfter, commentsCountBefore + 1);
    }

    @Test(description = "Add 2 new comments, delete one of them, verify successful deletion.")
    public void testAddCommentDeletion() {
        // add 2 comments
        String uniqueTimestamp = System.currentTimeMillis() + "";
        ecoNewsItemPage = ecoNewsItemPage.addComment(uniqueTimestamp);
        uniqueTimestamp = System.currentTimeMillis() + "";
        ecoNewsItemPage = ecoNewsItemPage.addComment(uniqueTimestamp);

        var oldFirstComment = ecoNewsItemPage.getFirstComment();
        String oldFirstCommentText = oldFirstComment.getCommentBodyText();

        ecoNewsItemPage = ecoNewsItemPage.deleteComment(oldFirstComment);

        var newFirstComment = ecoNewsItemPage.getFirstComment();
        String newFirstCommentText = newFirstComment.getCommentBodyText();

        Assert.assertNotEquals(newFirstCommentText, oldFirstCommentText);
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

        Assert.assertEquals(ecoNewsItemPage.getFirstComment().getCommentBodyText(), uniqueTimestamp,
                "The comment should not have been deleted.");

        ecoNewsItemPage = ecoNewsItemPage.deleteComment(comment);

        Assert.assertNotEquals(ecoNewsItemPage.getFirstComment().getCommentBodyText(), uniqueTimestamp,
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
                "Comments count should be : " + commentsCountBefore + 3);
    }
}
