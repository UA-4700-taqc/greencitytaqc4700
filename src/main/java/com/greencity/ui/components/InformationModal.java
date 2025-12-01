package com.greencity.ui.components;

import com.greencity.ui.Base;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InformationModal extends Base {
    @FindBy(css = "mat-dialog-container")
    private WebElement modalRoot;

    @Getter
    @FindBy(css = "button.m-btn.primary-global-button")
    private WebElement confirmButton;

    @Getter
    @FindBy(css = "button.m-btn.secondary-global-button")
    private WebElement cancelButton;

    @FindBy(css = "div.warning-title")
    private WebElement message;

    public InformationModal(WebDriver driver) {
        super(driver);
        waitUntilElementVisible(modalRoot);
    }

    public String getMessage() {
        return message.getText();
    }

    public void confirm() {
        confirmButton.click();
    }

    public void cancel() {
        cancelButton.click();
    }
}

