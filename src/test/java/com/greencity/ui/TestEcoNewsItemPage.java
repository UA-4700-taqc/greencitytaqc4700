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

public class TestEcoNewsItemPage extends TestRunnerWithUser {

    private EcoNewsItemPage ecoNewsItemPage;

    @BeforeMethod
    public void visitPage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.get("https://www.greencity.cx.ua/#/greenCity/news/149");
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
//        Assert.assertEquals(ecoNewsItemPage.getCommentsCount(), commentsCountBefore + 1);
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
}
