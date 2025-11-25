package com.greencity.ui.components.createnews;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActionButtonsSection extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//button[contains(@class,'primary-global-button')]")
    private WebElement publishBtn;

    @Getter
    @FindBy(xpath = ".//button[contains(@class,'secondary-global-button')]")
    private WebElement reviewBtn;

    @Getter
    @FindBy(xpath = ".//button[contains(@class,'tertiary-global-button')]")
    private WebElement exitBtn;

    public ActionButtonsSection(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
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
