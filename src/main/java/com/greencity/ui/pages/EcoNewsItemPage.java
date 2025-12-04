package com.greencity.ui.pages;

import com.greencity.ui.components.CommentComponent;
import com.greencity.ui.components.EcoNewsItemComponent;
import com.greencity.ui.pages.newspage.NewsPage;
import com.greencity.ui.utils.NewsTag;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class EcoNewsItemPage extends BasePage {

    private static final By COMMENTS_SELECTOR = By.cssSelector("app-comments-list div.comment-body-wrapper");
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

    @FindBy(css = "p.error-message")
    private WebElement errorMessage;

    public EcoNewsItemPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public EcoNewsItemPage reloadPage() {
        refreshPage();
        return new EcoNewsItemPage(driver);
    }

    private List<WebElement> getCommentRoots() {
        return driver.findElements(COMMENTS_SELECTOR);
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
        getWait(SHORT_WAIT_TIME).until(driver -> !(likesCount.getText().trim().equals(initialLikesCount)));
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
        if (recommendedNewsRoots.isEmpty()) return null;
        return new EcoNewsItemComponent(driver, recommendedNewsRoots.get(order));
    }

    public int getCommentsCount() {
        try {
            getWait(SHORT_WAIT_TIME).until(driver -> !(totalCommentsCountLabel.getText().trim().equals("0")));
        } catch (TimeoutException ignored) {}

        return Integer.parseInt(totalCommentsCountLabel.getText().trim());
    }

    public boolean isSubmitCommentButtonEnabled() {
        try {
            waitUntilElementEnabled(submitCommentButton);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSubmitCommentButtonDisabled() {
        try {
            waitUntilElementDisabled(submitCommentButton);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitSubmitCommentButtonEnabled() {
        waitUntilElementClickable(submitCommentButton);
    }

    public void typeComment(String text) {
        commentInput.clear();
        commentInput.sendKeys(text);
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    @Override
    public void typeLargeInput(WebElement element, String text) {
        threadJs.executeScript("arguments[0].innerText = arguments[1];", element, text);
        commentInput.sendKeys(" ");
        commentInput.sendKeys(Keys.DELETE);
    }

    public EcoNewsItemPage addComment(String text) {
        typeComment(text);
        waitSubmitCommentButtonEnabled();
        submitCommentButton.click();

        waitForCommentsUpdated();

        return this;
    }

    public void waitForCommentsUpdated() {
        int commentsCount = Integer.parseInt(totalCommentsCountLabel.getText().trim());
        if (commentsCount == 0) {
            waitUntilVisibilityOfElementLocated(COMMENTS_SELECTOR);
            return;
        }

        var list = getCommentRoots();
        var firstElement = list.getFirst();

        waitUntilElementStaleness(firstElement);
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

    public CommentComponent getCommentByText(String text) {
        return getComments().stream()
                .filter(c -> c.getCommentBodyText().equalsIgnoreCase(text.trim()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Comment not found: " + text));
    }

    public boolean doesExistCommentByText(String text) {
        return getComments().stream()
                .map(CommentComponent::getCommentBodyText)
                .anyMatch(c -> c.equalsIgnoreCase(text));
    }

    }