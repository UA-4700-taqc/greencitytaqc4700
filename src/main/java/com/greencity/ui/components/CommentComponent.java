package com.greencity.ui.components;

import com.greencity.ui.pages.EcoNewsItemPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class CommentComponent extends BaseComponent {
    @Getter
    @FindBy(css = "span.author-name")
    private WebElement authorName;

    @Getter
    @FindBy(css = "span.comment-date-month")
    private WebElement commentDate;

    @FindBy(css = "div.comment-likes img[alt='like']")
    private WebElement upperLikeImg;

    @FindBy(css = "div.comment-likes img[alt='dislike']")
    private WebElement dislikeImg;

    @FindBy(css = "span.like-amount")
    private WebElement likesAmount;

    @Getter
    @FindBy(css = "div.comment-text")
    private WebElement commentBody;

    @FindBy(css = "app-like-comment")
    private WebElement bottomLikeButton;

    @Getter
    @FindBy(css = "app-reply-comment")
    private WebElement replyCommentButton;

    @Getter
    @FindBy(css = "div.comment-textarea")
    private WebElement replyBody;

    @FindBy(css = "primary-global-button__reply")
    private WebElement sendReplyButton;

    @Getter
    @FindBy(css = "button.cta-btn.edit")
    private WebElement editCommentButton;

    @Getter
    @FindBy(css = "button.cta-btn.delete")
    private WebElement deleteCommentButton;

    @Getter
    @FindBy(css = "div.profile-avatar")
    private WebElement profileAvatar;

    @Getter
    @FindBy(css = "div[contenteditable='true']")
    private WebElement editCommentInput;

    @FindBy(css = "button.save-edit")
    private WebElement saveEditButton;

    @FindBy(css = "button.cancel-edit")
    private WebElement cancelEditButton;

    @Getter
    @FindBy(css = "span.edited")
    private WebElement editedCommentLabel;

    private static final By COMMENT_EDITED_LABEL_SELECTOR =
            By.cssSelector("span.edited");

    private WebElement commentRoot;

    public CommentComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        commentRoot = rootElement;
    }

    public String getAuthorNameText() {
        return authorName.getText().trim();
    }

    public String getCommentDateText() {
        return commentDate.getText().trim();
    }

    public void clickUpperLike() {
        upperLikeImg.click();
    }

    public void clickDislike() {
        dislikeImg.click();
    }

    public String getLikesAmount() {
        return likesAmount.getText().trim();
    }

    public String getCommentBodyText() {
        return commentBody.getText().trim();
    }

    public void clickBottomLikeButton() {
        bottomLikeButton.click();
    }

    public void clickReplyButton() {
        replyCommentButton.click();
    }

    public void fillReplyBody(String text) {
        replyBody.clear();
        replyBody.sendKeys(text);
    }

    public String getReplyBodyText() {
        return replyBody.getText().trim();
    }

    public void clickSendReplyButton() {
        sendReplyButton.click();
    }

    public InformationModal clickDeleteCommentButton() {
        deleteCommentButton.click();
        return new InformationModal(driver);
    }

    public void clickEditCommentButton() {
        editCommentButton.click();
    }

    public void clickSaveEditButton() {
        saveEditButton.click();
    }

    public InformationModal clickCancelEditButton() {
        cancelEditButton.click();
        return new InformationModal(driver);
    }

    public boolean isCommentEdited() {
        Duration originalWait = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        try {
            return !commentRoot.findElements(COMMENT_EDITED_LABEL_SELECTOR).isEmpty();
        } finally {
            driver.manage().timeouts().implicitlyWait(originalWait);
        }
    }

    public void waitForCommentDeletion() {
        waitUntilElementStaleness(commentRoot);
    }

    public void waitForCommentEdit() {
        Duration originalWait = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        try {
            waitUntilElementInvisible(editCommentInput);
        } finally {
            driver.manage().timeouts().implicitlyWait(originalWait);
        }
    }

    public EcoNewsItemPage deleteComment() {
        clickDeleteCommentButton().confirm();

        waitForCommentDeletion();
        return new EcoNewsItemPage(driver);
    }

    public EcoNewsItemPage editComment(String text) {
        clickEditCommentButton();

        editCommentInput.clear();
        editCommentInput.sendKeys(text);

        clickSaveEditButton();
        waitForCommentEdit();
        return new EcoNewsItemPage(driver);
    }
}
