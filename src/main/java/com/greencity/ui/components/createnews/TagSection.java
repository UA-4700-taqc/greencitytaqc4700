package com.greencity.ui.components.createnews;

import com.greencity.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.greencity.ui.enums.NewsTag;

public class TagSection extends BaseComponent {

    @FindBy(xpath = ".//div[contains(@class,'tags-block')]")
    private WebElement tagsBlock;

    @FindBy(xpath = ".//button[1]")
    private WebElement tagNewsBtn;

    @FindBy(xpath = ".//button[2]")
    private WebElement tagEventsBtn;

    @FindBy(xpath = ".//button[3]")
    private WebElement tagEducationBtn;

    @FindBy(xpath = ".//button[4]")
    private WebElement tagInitiativesBtn;

    @FindBy(xpath = ".//button[5]")
    private WebElement tagAdvertisingBtn;

    public TagSection(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
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
