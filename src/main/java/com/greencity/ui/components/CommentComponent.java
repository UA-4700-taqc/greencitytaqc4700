package com.greencity.ui.components;

import com.greencity.ui.pages.EcoNewsItemPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

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
    @FindBy(css = "app-reply-comment button")
    private WebElement replyCommentButton;

    @Getter
    @FindBy(css = "div.comment-textarea")
    private WebElement replyInput;

    @FindBy(css = "button.primary-global-button__reply")
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

    @Getter
    @FindBy(css = "app-view-replies button")
    private WebElement viewRepliesButton;

    @FindBy(css = "div.comment-body-wrapper.wrapper-reply")
    private WebElement replyItem;

    private static final By SHOW_REPLIES_BUTTON_SELECTOR =
            By.cssSelector("app-view-replies button");

    private static final By COMMENT_EDITED_LABEL_SELECTOR =
            By.cssSelector("span.edited");

    private static final By REPLY_INPUT_SELECTOR =
            By.cssSelector("div.comment-textarea");

    private static final By REPLY_ITEM_SELECTOR =
            By.cssSelector("div.comment-body-wrapper.wrapper-reply");

    private WebElement commentRoot;

    public CommentComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        commentRoot = rootElement;
    }

    private List<WebElement> getRepliesRoots() {
        return driver.findElements(REPLY_ITEM_SELECTOR);
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
        replyInput.clear();
        replyInput.sendKeys(text);
    }

    public String getReplyBodyText() {
        return replyInput.getText().trim();
    }

    public void clickSendReplyButton() {
        sendReplyButton.click();
    }

    public void waitSendReplyButtonEnabled() {
        waitUntilElementClickable(sendReplyButton);
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
        return isVisibleElementLocated(COMMENT_EDITED_LABEL_SELECTOR);
    }

    public boolean hasReplies() {
        return isVisibleElementLocated(SHOW_REPLIES_BUTTON_SELECTOR);
    }

    public void waitForCommentDeletion() {
        waitUntilElementStaleness(commentRoot);
    }

    public void waitForCommentEdit() {
        waitUntilElementInvisible(editCommentInput);
    }

    public void waitForCommentReply() {
        if (isVisibleElementLocated(REPLY_ITEM_SELECTOR)) {
            int initialSize = getRepliesRoots().size();
            getWait(SHORT_WAIT_TIME).until(driver -> (getRepliesRoots().size() > initialSize));
            return;
        }

        waitUntilElementVisible(replyItem);
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

    public EcoNewsItemPage addReply(String text) {
        if (!isVisibleElementLocated(REPLY_INPUT_SELECTOR)) {
            clickReplyButton();
        }

        fillReplyBody(text);
        waitSendReplyButtonEnabled();
        clickSendReplyButton();

        waitForCommentReply();
        return new EcoNewsItemPage(driver);
    }

}
