package com.greencity.ui.components.createnews;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.greencity.ui.enums.NewsTag;

public class TagSection extends BaseComponent {

    @FindBy(xpath = ".//div[contains(@class,'tags-block')]")
    private WebElement tagsBlock;

    @Getter
    @FindBy(xpath = ".//button[1]")
    private WebElement tagNewsBtn;

    @Getter
    @FindBy(xpath = ".//button[2]")
    private WebElement tagEventsBtn;

    @Getter
    @FindBy(xpath = ".//button[3]")
    private WebElement tagEducationBtn;

    @Getter
    @FindBy(xpath = ".//button[4]")
    private WebElement tagInitiativesBtn;

    @Getter
    @FindBy(xpath = ".//button[5]")
    private WebElement tagAdvertisingBtn;

    public TagSection(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void selectTag(NewsTag tag) {
        getTagElement(tag).click();
    }

    public WebElement getTagElement(NewsTag tag) {
        return switch (tag) {
            case NEWS -> tagNewsBtn;
            case EVENTS -> tagEventsBtn;
            case EDUCATION -> tagEducationBtn;
            case INITIATIVES -> tagInitiativesBtn;
            case ADVERTISING -> tagAdvertisingBtn;
        };
    }
}
