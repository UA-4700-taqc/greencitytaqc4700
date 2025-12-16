package com.greencity.ui.pages;

import com.greencity.ui.components.createnews.*;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

public class CreateEcoNewsItemPage extends BasePage {

    public final HeaderSection header;
    public final TagSection tags;
    public final ImageSection image;
    public final ContentSection content;
    public final MetaSection meta;
    public final ActionButtonsSection actions;
    @FindBy(css = ".title")
    private WebElement headerRoot;
    @FindBy(css = ".tags-block")
    private WebElement tagsRoot;
    @Getter
    @FindBy(css = ".image-block")
    private WebElement imageRoot;
    @FindBy(css = ".form-container")
    private WebElement contentRoot;
    @FindBy(css = ".date")
    private WebElement metaRoot;
    @FindBy(css = ".submit-buttons")
    private WebElement actionsRoot;
    @FindBy(xpath = "//div[contains(@class,'success') or contains(@class,'snack') or contains(@class,'notification')]")
    private WebElement successMessage;

    public CreateEcoNewsItemPage(WebDriver driver) {
        super(driver);

        this.header = new HeaderSection(driver, headerRoot);
        this.tags = new TagSection(driver, tagsRoot);
        this.image = new ImageSection(driver, imageRoot);
        this.content = new ContentSection(driver, contentRoot);
        this.meta = new MetaSection(driver, metaRoot);
        this.actions = new ActionButtonsSection(driver, actionsRoot);
    }

    public void visitPage() {
        try {
            String host = new URL(driver.getCurrentUrl()).getHost();
            driver.get("https://" + host + "/#/greenCity/news/create-news");
        } catch (Exception e) {
            return;
        }
        wait.until(ExpectedConditions.visibilityOf(headerRoot));
    }

    public String waitForSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement msg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'success') or contains(@class,'snack') or contains(@class,'notification')]")
                )
        );
        return msg.getText().trim();
    }

}

