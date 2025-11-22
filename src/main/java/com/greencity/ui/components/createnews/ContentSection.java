package com.greencity.ui.components.createnews;

import com.greencity.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContentSection extends BaseComponent {

    @FindBy(xpath = "//input[contains(@class,'ng')]")
    private WebElement sourceInput;

    @FindBy(xpath = "//textarea[@formcontrolname='title']")
    private WebElement titleInput;

    @FindBy(xpath = "//div[@class='ql-editor']")
    private WebElement contentInput;

    public ContentSection(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
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
