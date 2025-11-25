package com.greencity.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.greencity.ui.components.createnews.HeaderSection;
import com.greencity.ui.components.createnews.TagSection;
import com.greencity.ui.components.createnews.ImageSection;
import com.greencity.ui.components.createnews.ContentSection;
import com.greencity.ui.components.createnews.MetaSection;
import com.greencity.ui.components.createnews.ActionButtonsSection;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateEcoNewsItemPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement headerRoot;

    @FindBy(css = ".tags-block")
    private WebElement tagsRoot;

    @FindBy(css = ".image-block")
    private WebElement imageRoot;

    @FindBy(css = ".form-container")
    private WebElement contentRoot;

    @FindBy(css = ".date")
    private WebElement metaRoot;

    @FindBy(css = ".submit-buttons")
    private WebElement actionsRoot;

    public final HeaderSection header;
    public final TagSection tags;
    public final ImageSection image;
    public final ContentSection content;
    public final MetaSection meta;
    public final ActionButtonsSection actions;

    public CreateEcoNewsItemPage(WebDriver driver) {
        super(driver);

        this.header  = new HeaderSection(driver, headerRoot);
        this.tags    = new TagSection(driver, tagsRoot);
        this.image   = new ImageSection(driver, imageRoot);
        this.content = new ContentSection(driver, contentRoot);
        this.meta    = new MetaSection(driver, metaRoot);
        this.actions = new ActionButtonsSection(driver, actionsRoot);
    }

    public void visitPage() {

        driver.get("/#/greenCity/news/create-news");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(headerRoot));
    }


}

