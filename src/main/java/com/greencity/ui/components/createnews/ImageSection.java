package com.greencity.ui.components.createnews;

import com.greencity.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImageSection extends BaseComponent {

    @FindBy(xpath = ".//div[@class='image-block']//h3")
    private WebElement imageLabel;

    @FindBy(xpath = ".//label[@for='upload']")
    private WebElement uploadLabel;

    @FindBy(id = "upload")
    private WebElement uploadInput;

    @FindBy(xpath = ".//button[contains(@class,'secondary-global-button')]")
    private WebElement cancelBtn;

    @FindBy(xpath = ".//button[contains(@class,'primary-global-button')]")
    private WebElement submitBtn;

    public ImageSection(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
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
