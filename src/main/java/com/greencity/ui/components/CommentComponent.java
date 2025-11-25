package com.greencity.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommentComponent extends BaseComponent {
    @FindBy(css = "span.author-name")
    private WebElement authorName;

    @FindBy(css = "span.comment-date-month")
    private WebElement commentDate;

    @FindBy(css = "div.comment-likes img[alt='like']")
    private WebElement upperLikeImg;

    @FindBy(css = "div.comment-likes img[alt='dislike']")
    private WebElement dislikeImg;

    @FindBy(css = "span.like-amount")
    private WebElement likesAmount;

    @FindBy(css = "div.comment-text")
    private WebElement commentBody;

    @FindBy(css = "app-like-comment")
    private WebElement bottomLikeButton;

    @FindBy(css = "app-reply-comment")
    private WebElement replyButton;

    @FindBy(css = "div.comment-textarea")
    private WebElement replyBody;

    @FindBy(css = "primary-global-button__reply")
    private WebElement sendReplyButton;

    @FindBy(css = "button.cta-btn.delete")
    private WebElement deleteCommentButton;

    public CommentComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getAuthorName() {
        return authorName.getText().trim();
    }

    public String getCommentDate() {
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
        replyButton.click();
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
}
