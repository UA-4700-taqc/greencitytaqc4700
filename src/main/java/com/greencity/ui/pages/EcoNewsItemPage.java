package com.greencity.ui.pages;

import com.greencity.ui.components.CommentComponent;
import com.greencity.ui.components.EcoNewsItemComponent;
import com.greencity.ui.components.InformationModal;
import com.greencity.ui.pages.newspage.NewsPage;
import com.greencity.ui.utils.NewsTag;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class EcoNewsItemPage extends BasePage {

    @FindBy(css = "div.back-button a")
    private WebElement goBackButton;

    @FindBy(css = "div.tags-item")
    private List<WebElement> tags;

    @FindBy(css = "div.news-title")
    private WebElement newsTitle;

    @FindBy(css = "div.news-info-date")
    private WebElement newsDate;

    @FindBy(css = "div.news-info-author")
    private WebElement newsAuthor;

    @FindBy(css = "div.like_wr img[alt='like']")
    private WebElement likeButton;

    @FindBy(css = "div.like_wr span.numerosity_likes")
    private WebElement likesCount;

    @FindBy(css = "img[alt='news-image']")
    private WebElement newsImage;

    @FindBy(css = "div.news-text-content div")
    private WebElement newsText;

    @FindBy(css = "img[alt='twitter']")
    private WebElement twitterButton;

    @FindBy(css = "img[alt='linkedin']")
    private WebElement linkedinButton;

    @FindBy(css = "img[alt='facebook']")
    private WebElement facebookButton;

    @FindBy(css = "app-news-list-gallery-view")
    private List<WebElement> recommendedNewsRoots;

    @FindBy(css = "span#total-count")
    private WebElement totalCommentsCountLabel;

    @Getter
    @FindBy(css = "div.comment-textarea")
    private WebElement commentInput;

    @Getter
    @FindBy(css = "button.primary-global-button")
    private WebElement submitCommentButton;

    private static final By COMMENTS_SELECTOR =
            By.cssSelector("app-comments-list div.comment-body-wrapper");

    private static final By COMMENTS_COUNTER =
            By.cssSelector("span#total-count");

    private List<WebElement> getCommentRoots() {
        return driver.findElements(COMMENTS_SELECTOR);
    }

    public EcoNewsItemPage(WebDriver driver) {
        super(driver);
    }

    public NewsPage GoBack() {
        goBackButton.click();
        return new NewsPage(driver);
    }

    public List<NewsTag> getTags() {
        return tags.stream()
                .map(WebElement::getText)
                .map(NewsTag::getTagFromText)
                .collect(Collectors.toList());
    }

    public String getTitle() {
        return newsTitle.getText().trim();
    }

    public String getDate() {
        return newsDate.getText().trim();
    }

    public String getAuthor() {
        return newsAuthor.getText().trim();
    }

    public EcoNewsItemPage clickLike() {
        String initialLikesCount = likesCount.getText().trim();
        likeButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> !(likesCount.getText().trim().equals(initialLikesCount)));
        return this;
    }

    public int getLikesCount() {
        return Integer.parseInt(likesCount.getText().trim());
    }

    public String getImageSrc() {
        return newsImage.getAttribute("src");
    }

    public String getText() {
        return newsText.getText().trim();
    }

    public List<EcoNewsItemComponent> getRecommendedNews() {
        return recommendedNewsRoots.stream()
                .map(webElement -> new EcoNewsItemComponent(driver, webElement))
                .collect(Collectors.toList());
    }

    public EcoNewsItemComponent getRecommendedNewsByOrder(int order) {
        if (recommendedNewsRoots.isEmpty())
            return null;
        return new EcoNewsItemComponent(driver, recommendedNewsRoots.get(order));
    }

    public int getCommentsCount() {
        return Integer.parseInt(totalCommentsCountLabel.getText().trim());
    }

    public boolean isSubmitCommentButtonEnabled() {
        return submitCommentButton.isEnabled();
    }

    public boolean isSubmitCommentButtonDisabled() {
        return !submitCommentButton.isEnabled();
    }

    public void waitSubmitCommentButtonEnabled() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(submitCommentButton));
    }

    public void waitSubmitCommentButtonDisabled() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> !(submitCommentButton.isEnabled()));
    }

    public void typeComment(String text) {
        commentInput.clear();
        commentInput.sendKeys(text);
    }

    public EcoNewsItemPage addComment(String text) {
        typeComment(text);
        waitSubmitCommentButtonEnabled();
        submitCommentButton.click();

        waitForCommentsUpdated();

        return this;
    }

    public void waitForCommentsUpdated() {
        int oldCount = Integer.parseInt(totalCommentsCountLabel.getText().trim());

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    try {
                        int newCount = Integer.parseInt(
                                d.findElement(COMMENTS_COUNTER).getText().trim()
                        );
                        return newCount > oldCount;
                    } catch (StaleElementReferenceException e) {
                        return false;
                    }
                });
    }

    public void waitForCommentsUpdatedDeletion() {
        var list = getCommentRoots();
        var firstElement = list.getFirst();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.stalenessOf(firstElement));
    }

    public List<CommentComponent> getComments() {
        var commentsRoots = getCommentRoots();
        return commentsRoots
                .stream()
                .map(root -> new CommentComponent(driver, root))
                .collect(Collectors.toList());
    }

    public CommentComponent getFirstComment() {
        List<CommentComponent> list = getComments();
        if (list.isEmpty()) return null;

        return list.getFirst();
    }

    public EcoNewsItemPage deleteComment(CommentComponent comment) {
        InformationModal modal = comment.clickDeleteCommentButton();

        modal.confirm();

        waitForCommentsUpdatedDeletion();
        return this;
    }
}