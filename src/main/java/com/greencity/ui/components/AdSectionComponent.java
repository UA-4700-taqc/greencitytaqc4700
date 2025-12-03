package com.greencity.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AdSectionComponent extends BaseComponent {

    private static final Duration ELEMENT_CLICK_TIMEOUT = Duration.ofSeconds(20);

    @FindBy(xpath = "//div[@id='main-content']/h1")
    private WebElement title;

    @FindBy(xpath = "//div[@id='main-content']/p")
    private WebElement infoText;

    @FindBy(id = "guy-image")
    private WebElement illustrationImage;

    @FindBy(xpath = "//div[@id='main-content']/button")
    private WebElement startFormingHabitButton;

    public AdSectionComponent(WebDriver driver, WebElement adSectionRoot) {
        super(driver, adSectionRoot);
    }

    public boolean isAdSectionVisible() {
        return rootElement.isDisplayed();
    }

    public String getTitleText() {
        return title.getText().trim();
    }

    public String getInfoText() {
        return infoText.getText().trim();
    }

    public boolean isImageDisplayed() {
        return illustrationImage.isDisplayed();
    }

    public boolean isButtonDisplayed() {
        return startFormingHabitButton.isDisplayed();
    }

    public void clickStartFormingHabitButton() {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_CLICK_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(startFormingHabitButton));
        startFormingHabitButton.click();
    }
    public WebElement getStartFormingHabitButton() {
        return startFormingHabitButton;
    }
}