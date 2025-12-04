package com.greencity.ui.components;

import com.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubscriptionSectionComponent extends BasePage {

    @FindBy(xpath = "//section[@id='subscription']")
    private WebElement subscriptionSectionRoot;

    @FindBy(xpath = "//section[@id='subscription']//h2")
    private WebElement sectionTitle;

    @FindBy(xpath = "//div[@id='qr-code-wrapper']/img")
    private WebElement qrCodeImage;

    @FindBy(xpath = "//div[@id='form-wrapper']/p")
    private WebElement infoText;

    @FindBy(xpath = "//div[contains(@class, 'form-input')]/input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//div[contains(@class, 'form-input')]/button")
    private WebElement subscribeButton;

    @FindBy(xpath = "//p[@id='validation-error']")
    private WebElement validationError;

    public SubscriptionSectionComponent(WebDriver driver) {
        super(driver);
        waitUntilElementVisible(subscriptionSectionRoot);
    }

    public SubscriptionSectionComponent scrollToSection() {
        scrollIntoView(subscriptionSectionRoot);
        return this;
    }

    public boolean isSectionDisplayed() {
        return subscriptionSectionRoot.isDisplayed();
    }

    public String getSectionTitleText() {
        waitUntilElementVisible(sectionTitle);
        return sectionTitle.getText().trim();
    }

    public boolean isQrCodeImageDisplayed() {
        waitUntilElementVisible(qrCodeImage);
        return qrCodeImage.isDisplayed();
    }

    public String getInfoText() {
        waitUntilElementVisible(infoText);
        return infoText.getText().trim();
    }

    public String getEmailInputPlaceholder() {
        waitUntilElementVisible(emailInput);
        return emailInput.getAttribute("placeholder");
    }

    public SubscriptionSectionComponent enterEmail(String email) {
        waitUntilElementClickable(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public void clickSubscribeButton() {
        waitUntilElementClickable(subscribeButton);
        subscribeButton.click();
    }

    public String getValidationError() {
        waitUntilElementVisible(validationError);
        return validationError.getText().trim();
    }

    public boolean isValidationErrorDisplayed() {
        try {
            return validationError.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e)
        {
            return false;
        }
    }

    public WebElement getValidationErrorElement() {
        return validationError;
    }

    public WebElement getSubscribeButton() {
        return subscribeButton;
    }

}
