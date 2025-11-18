package com.greencity.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateEcoNewsItemPage extends BasePage {

    public final HeaderSection header;
    public final TagSection tags;
    public final ImageSection image;
    public final ContentSection content;
    public final MetaSection meta;
    public final ActionButtonsSection actions;

    public CreateEcoNewsItemPage(WebDriver driver) {
        super(driver);
        this.header = new HeaderSection(driver);
        this.tags = new TagSection(driver);
        this.image = new ImageSection(driver);
        this.content = new ContentSection(driver);
        this.meta = new MetaSection(driver);
        this.actions = new ActionButtonsSection(driver);
    }

    public enum NewsTag {
        NEWS,
        EVENTS,
        EDUCATION,
        INITIATIVES,
        ADVERTISING;
    }

    public static class HeaderSection {
        private final WebDriver driver;

        @FindBy(xpath = "//h2")
        private WebElement titleHeader;

        @FindBy(xpath = "//p[contains(@class,'title-description')]")
        private WebElement titleDescription;

        public HeaderSection(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public String getHeader() {
            return titleHeader.getText().trim();
        }

        public String getDescription() {
            return titleDescription.getText().trim();
        }
    }

    public static class TagSection {
        private final WebDriver driver;

        @FindBy(xpath = "//div[contains(@class,'tags-block')]")
        private WebElement tagsBlock;

        @FindBy(xpath = "//app-tags-select//button[1]")
        private WebElement tagNewsBtn;

        @FindBy(xpath = "//app-tags-select//button[2]")
        private WebElement tagEventsBtn;

        @FindBy(xpath = "//app-tags-select//button[3]")
        private WebElement tagEducationBtn;

        @FindBy(xpath = "//app-tags-select//button[4]")
        private WebElement tagInitiativesBtn;

        @FindBy(xpath = "//app-tags-select//button[5]")
        private WebElement tagAdvertisingBtn;

        public TagSection(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void selectTag(NewsTag tag) {
            switch (tag) {
                case NEWS -> tagNewsBtn.click();
                case EVENTS -> tagEventsBtn.click();
                case EDUCATION -> tagEducationBtn.click();
                case INITIATIVES -> tagInitiativesBtn.click();
                case ADVERTISING -> tagAdvertisingBtn.click();
            }
        }
    }

    public static class ImageSection {
        private final WebDriver driver;

        @FindBy(xpath = "//div[@class='image-block']//h3")
        private WebElement imageLabel;

        @FindBy(xpath = "//label[@for='upload']")
        private WebElement uploadLabel;

        @FindBy(id = "upload")
        private WebElement uploadInput;

        @FindBy(xpath = "//button[contains(@class,'secondary-global-button')]")
        private WebElement cancelBtn;

        @FindBy(xpath = "//button[contains(@class,'primary-global-button')]")
        private WebElement submitBtn;

        public ImageSection(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void uploadImage(String filePath) {
            uploadInput.sendKeys(filePath);
        }

        public void confirmUpload() {
            submitBtn.click();
        }

        public void cancelUpload() {
            cancelBtn.click();
        }
    }

    public static class ContentSection {
        private final WebDriver driver;

        @FindBy(xpath = "//input[contains(@class,'ng')]")
        private WebElement sourceInput;

        @FindBy(xpath = "//textarea[@formcontrolname='title']")
        private WebElement titleInput;

        @FindBy(xpath = "//div[@class='ql-editor']")
        private WebElement contentInput;

        public ContentSection(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void enterSource(String text) {
            sourceInput.clear();
            sourceInput.sendKeys(text);
        }

        public void enterTitle(String title) {
            titleInput.clear();
            titleInput.sendKeys(title);
        }

        public void enterContent(String text) {
            contentInput.sendKeys(text);
        }
    }

    public static class MetaSection {
        private final WebDriver driver;

        @FindBy(xpath = "//div[@class='date']/p[1]/span[2]")
        private WebElement creationDate;

        @FindBy(xpath = "//div[@class='date']/p[2]/span[2]")
        private WebElement authorName;

        public MetaSection(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public String getCreationDate() {
            return creationDate.getText().trim();
        }

        public String getAuthorName() {
            return authorName.getText().trim();
        }
    }

    public static class ActionButtonsSection {
        private final WebDriver driver;

        @FindBy(xpath = "//button[contains(@class,'primary-global-button')]")
        private WebElement publishBtn;

        @FindBy(xpath = "//button[contains(@class,'secondary-global-button')]")
        private WebElement reviewBtn;

        @FindBy(xpath = "//button[contains(@class,'tertiary-global-button')]")
        private WebElement exitBtn;

        public ActionButtonsSection(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void clickPublish() {
            publishBtn.click();
        }

        public void clickReview() {
            reviewBtn.click();
        }

        public void clickExit() {
            exitBtn.click();
        }
    }
}

