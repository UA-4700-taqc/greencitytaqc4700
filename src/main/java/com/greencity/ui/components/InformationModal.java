package com.greencity.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InformationModal {
    private final WebDriver driver;

    @FindBy(css = "mat-dialog-container")
    private WebElement modalRoot;

    @FindBy(css = "button.m-btn.primary-global-button")
    private WebElement confirmButton;

    @FindBy(css = "button.m-btn.secondary-global-button")
    private WebElement cancelButton;

    public InformationModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilVisible();
    }

    private void waitUntilVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(modalRoot));
    }

    public void confirm() {
        confirmButton.click();
    }

    public void cancel() {
        cancelButton.click();
    }
}

