package com.greencity.ui.components.createnews;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.CreateEcoNewsPreviewPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ActionButtonsSection extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//button[contains(@class,'primary-global-button')]")
    private WebElement publishBtn;

    @Getter
    @FindBy(xpath = ".//button[contains(@class,'secondary-global-button')]")
    private WebElement previewBtn;

    @Getter
    @FindBy(xpath = ".//button[contains(@class,'tertiary-global-button')]")
    private WebElement exitBtn;

    public ActionButtonsSection(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickPublish() {
        publishBtn.click();
    }

    public CreateEcoNewsPreviewPage clickPreview() {

        previewBtn.click();
        return new CreateEcoNewsPreviewPage(driver);
    }

    public void clickExit() {
        exitBtn.click();
    }
}
