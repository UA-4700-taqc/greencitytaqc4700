package com.greencity.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AdSectionComponent extends BaseComponent {

    private static final Long CLICKABLE_WAIT_TIMEOUT = 20L;

    @FindBy(xpath = "//div[@id='main-content']/h1")
    private WebElement title;

    @FindBy(xpath = "//div[@id='main-content']/p")
    private WebElement infoText;

    @FindBy(id = "guy-image")
    private WebElement illustrationImage;

    @FindBy(xpath = ".//button[contains(normalize-space(.), 'Почати формувати звичку!')]")
    private WebElement startFormingHabitButton;

    public AdSectionComponent(WebDriver driver, WebElement adSectionRoot) {
        super(driver, adSectionRoot);
    }

    public boolean isAdSectionVisible() {
        return title.isDisplayed();
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

    public String getButtonText() {
        getWait(5L).until(ExpectedConditions.visibilityOf(startFormingHabitButton));
        return startFormingHabitButton.getText().trim();
    }

    public void clickStartFormingHabitButton() {
        getWait(CLICKABLE_WAIT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(startFormingHabitButton));
        startFormingHabitButton.click();
        sleep(1000);
    }

}