package com.greencity.ui.pages;

import com.google.j2objc.annotations.Weak;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateEcoNewsPreviewPage extends BasePage {

    @FindBy(css = ".news-title.word-wrap")
    private WebElement newsTitle;

    @FindBy(className = "news-info-date")
    private  WebElement newsDate;

    @FindBy(className = "news-info-author")
    private WebElement newsAuthor;

    @FindBy(className = "news-image-img")
    private WebElement newsImage;

    @FindBy(css = ".news-text-content.word-wrap")
    private WebElement newsText;

    @FindBy(css = ".source-text.word-wrap")
    private WebElement sourceText;

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
}
