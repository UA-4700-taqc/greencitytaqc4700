package com.greencity.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateEcoNewsItemPage extends BasePage {

    public enum NewsTag {
        NEWS(1), EVENTS(2), EDUCATION(3), INITIATIVES(4), ADVERTISING(5);
        private final int index;
        NewsTag(int index) { this.index = index; }
        public int getIndex() { return index; }
    }

    @FindBy(xpath = "//h2")
    private WebElement titleHeader;

    @FindBy(xpath = "//p[contains(@class,'title-description')]")
    private WebElement titleDescription;

    @FindBy(xpath = "//div[@class='title-block']//h3")
    private WebElement newsTitleLabel;

    @FindBy(xpath = "//textarea[contains(@class,'ng')]")
    private WebElement newsTitleInputField;

    @FindBy(xpath = "//div[contains(@class,'tags-block')]")
    private WebElement tagBlock;

    @FindBy(xpath = "//div[contains(@class,'tags-block')]//h3")
    private WebElement tagBlockText1;

    @FindBy(xpath = "//div[contains(@class,'tags-block')]//h3/p")
    private WebElement tagBlockText2;

    @FindBy(xpath = "//app-tags-select//button[1]")
    private WebElement tagBtnNews;

    @FindBy(xpath = "//app-tags-select//button[2]")
    private WebElement tagBtnEvents;

    @FindBy(xpath = "//app-tags-select//button[3]")
    private WebElement tagBtnEducation;

    @FindBy(xpath = "//app-tags-select//button[4]")
    private WebElement tagBtnInitiatives;

    @FindBy(xpath = "//app-tags-select//button[5]")
    private WebElement tagBtnAdvertizing;

    @FindBy(xpath = "//div[@class='image-block']//h3")
    private WebElement imageLabel;

    @FindBy(xpath = "//div[contains(@class,'centered')]")
    private WebElement imageText;

    @FindBy(xpath = "//label[@for='upload']")
    private WebElement imageLable;

    @FindBy(xpath = "//input[@id='upload']")
    private WebElement imageInput;

    @FindBy(xpath = "//button[contains(@class,'secondary-global-button')]")
    private WebElement imageUploadCancelBtn;

    @FindBy(xpath = "//button[contains(@class,'primary-global-button')]")
    private WebElement imageUploadSubmitBtn;

    @FindBy(xpath = "//p[contains(@class,'warning')]")
    private WebElement imageWarning;

    @FindBy(xpath = "//div[@class='source-block']//h3")
    private WebElement sourceLabel;

    @FindBy(xpath = "//span[contains(@class,'field-info')]")
    private WebElement sourceComment;

    @FindBy(xpath = "//input[contains(@class,'ng')]")
    private WebElement sourceInputField;

    @FindBy(xpath = "//div[@class='textarea-wrapper']//h3")
    private WebElement contentLabel;

    @FindBy(xpath = "//div[@class='textarea-wrapper']//h3/p")
    private WebElement contentComment;

    @FindBy(xpath = "//div[contains(@class,'ql-toolbar')]")
    private WebElement contentToolbar;

    @FindBy(xpath = "//div[@class='ql-editor']")
    private WebElement contentInputTemplate;

    @FindBy(xpath = "//div[@class='ql-editor']/p")
    private WebElement contentInputField;

    @FindBy(xpath = "//p[contains(@class,'quill-counter')]")
    private WebElement contentInputWarning;

    @FindBy(xpath = "//div[@class='date']/p[1]/span[1]")
    private WebElement creationDateLabel;

    @FindBy(xpath = "//div[@class='date']/p[1]/span[2]")
    private WebElement creationDate;

    @FindBy(xpath = "//div[@class='date']/p[2]/span[1]")
    private WebElement authorLabel;

    @FindBy(xpath = "//div[@class='date']/p[2]/span[2]")
    private WebElement author;

    @FindBy(xpath = "//button[contains(@class,'primary-global-button')]")
    private WebElement publishBtn;

    @FindBy(xpath = "//button[contains(@class,'secondary-global-button')]")
    private WebElement reviewBtn;

    @FindBy(xpath = "//button[contains(@class,'tertiary-global-button')]")
    private WebElement exitBtn;

    public CreateEcoNewsItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getText(WebElement element) {
        return element.getText().trim();
    }

    public void clickTag(NewsTag tag) {
        driver.findElement(org.openqa.selenium.By.xpath("//app-tags-select//button[" + tag.getIndex() + "]")).click();
    }

    public void clickPublish() { publishBtn.click(); }
    public void clickReview() { reviewBtn.click(); }
    public void clickExit() { exitBtn.click(); }

    public String getTitleHeader() { return getText(titleHeader); }
    public String getTitleDescription() { return getText(titleDescription); }

    public String getNewsTitleLabel() { return getText(newsTitleLabel); }

    public String getTagBlockLabel() { return getText(tagBlockText1); }
    public String getTagBlockComment() { return getText(tagBlockText2); }

    public String getImageLabel() { return getText(imageLabel); }
    public String getImageWarning() { return getText(imageWarning); }

    public String getSourceLabel() { return getText(sourceLabel); }
    public String getSourceComment() { return getText(sourceComment); }

    public String getContentLabel() { return getText(contentLabel); }
    public String getContentComment() { return getText(contentComment); }
    public String getContentWarning() { return getText(contentInputWarning); }

    public String getCreationDateLabel() { return getText(creationDateLabel); }
    public String getCreationDate() { return getText(creationDate); }

    public String getAuthorLabel() { return getText(authorLabel); }
    public String getAuthor() { return getText(author); }

    public String getPublishBtnText() { return getText(publishBtn); }
    public String getReviewBtnText() { return getText(reviewBtn); }
    public String getExitBtnText() { return getText(exitBtn); }
}
