package com.greencity.ui.components.auth;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

public class SignInModal extends BaseComponent {

    // ===== HEADER / CLOSE =====
    @Getter
    @FindBy(css = "a.close-modal-window")
    private WebElement closeButton;

    // ===== TEXT CONTENT =====
    @Getter
    @FindBy(xpath = ".//h1[contains(text(),'Welcome back')]")
    private WebElement title;

    @Getter
    @FindBy(xpath = ".//h2[contains(text(),'Please enter your details')]")
    private WebElement subtitle;

    // ===== INPUTS =====
    @Getter
    @FindBy(id = "email")
    private WebElement emailInput;

    @Getter
    @FindBy(id = "password")
    private WebElement passwordInput;

    // ===== SHOW/HIDE PASSWORD =====
    @Getter
    @FindBy(css = ".show-hide-btn")
    private WebElement showHidePasswordButton;

    // ===== ERROR MESSAGES =====
    @Getter
    @FindBy(id = "email-err-msg")
    private WebElement emailErrorMessageContainer;

    @Getter
    @FindBy(id = "pass-err-msg")
    private WebElement passwordErrorMessageContainer;

    // ===== BUTTONS =====
    @Getter
    @FindBy(css = "button[type='submit']")
    private WebElement signInButton;

    @Getter
    @FindBy(css = "app-google-btn .google-sign-in")
    private WebElement googleSignInButton;

    // ===== LINKS =====
    @Getter
    @FindBy(xpath = ".//a[contains(text(),'Forgot password')]")
    private WebElement forgotPasswordLink;

    @Getter
    @FindBy(xpath = ".//a[contains(text(),'Sign up')]")
    private WebElement signUpLink;

    // ===== Constructor =====

    public SignInModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    public SignInModal(WebDriver driver) {
        super(driver, driver.findElement(By.xpath("//app-auth-modal")));
    }

    // ===== Methods =====

    private void clickElement(WebElement element) {
        waitUntilElementClickable(element);
        element.click();
    }

    public void enterEmail(String email) {
        waitUntilElementVisible(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        waitUntilElementVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickSignIn() { clickElement(signInButton); }

    public void clickShowHidePassword() { clickElement(showHidePasswordButton); }

    public void clickForgotPassword() { clickElement(forgotPasswordLink); }

    public void clickSignUp() { clickElement(signUpLink); }

    public void clickGoogleLogin() { clickElement(googleSignInButton); }

    public void close() { clickElement(closeButton); }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }
    public boolean isDisplayed() {
        return rootElement.isDisplayed();
    }
}