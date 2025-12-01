package com.greencity.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateEcoNewsPreviewPage extends BasePage {

    @FindBy(css = ".news-title.word-wrap")
    private WebElement newsTitle;

    @FindBy(className = "news-info-date")
    private WebElement newsDate;

    @FindBy(className = "news-info-author")
    private WebElement newsAuthor;

    @FindBy(className = "news-image-img")
    private WebElement newsImage;

    @FindBy(css = ".news-text-content.word-wrap")
    private WebElement newsText;

    @FindBy(css = ".source-text.word-wrap")
    private WebElement sourceText;

    @Getter
    @FindBy(className = "button-link")
    private WebElement backToEditingButton;

    public CreateEcoNewsPreviewPage(WebDriver driver) {
        super(driver);
        waitForPreviewLoaded();
    }

    private void waitForPreviewLoaded() {
        wait.until(ExpectedConditions.visibilityOf(newsTitle));
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

    public String getContent() {
        return newsText.getText().trim();
    }

    public String getSource() {
        return sourceText.getText().trim();
    }

    public String getLink() {
        return backToEditingButton.getAttribute("href");
    }

    public CreateEcoNewsItemPage clickBackToReview() {

        backToEditingButton.click();
        return new CreateEcoNewsItemPage(driver);
    }
}
