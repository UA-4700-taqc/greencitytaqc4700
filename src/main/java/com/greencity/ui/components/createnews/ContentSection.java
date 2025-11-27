package com.greencity.ui.components.createnews;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContentSection extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//input[contains(@class,'ng')]")
    private WebElement sourceInput;

    @Getter
    @FindBy(xpath = ".//textarea[@formcontrolname='title']")
    private WebElement titleInput;

    @Getter
    @FindBy(xpath = ".//span[contains(@class,'field-info')]")
    private WebElement titleSymbolCount;

    @FindBy(xpath = ".//quill-editor//*[contains(@class, 'ql-editor')]")
    private WebElement contentInput;

    public ContentSection(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
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
        wait.until(ExpectedConditions.elementToBeClickable(contentInput));
//        threadJs.executeScript("arguments[0].innerHTML = arguments[1];", contentInput, text);
//        actions.moveToElement(contentInput).click().sendKeys(Keys.ENTER).perform();
        contentInput.sendKeys(text);
    }
}
