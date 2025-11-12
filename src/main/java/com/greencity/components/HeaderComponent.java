package com.greencity.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * HeaderComponent represents the header section of the GreenCity application.
 * This component uses Page Object Model pattern to encapsulate header elements and actions.
 */
public class HeaderComponent {
    
    private WebDriver driver;
    
    @FindBy(css = "header")
    private WebElement header;
    
    @FindBy(css = "header .logo")
    private WebElement logo;
    
    @FindBy(css = "header nav")
    private WebElement navigationMenu;
    
    @FindBy(css = "header .sign-in-link")
    private WebElement signInLink;
    
    @FindBy(css = "header .sign-up-link")
    private WebElement signUpLink;
    
    @FindBy(css = "header .user-profile")
    private WebElement userProfile;
    
    /**
     * Constructor to initialize HeaderComponent with WebDriver.
     * 
     * @param driver WebDriver instance
     */
    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Check if header is displayed on the page.
     * 
     * @return true if header is displayed, false otherwise
     */
    public boolean isHeaderDisplayed() {
        try {
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if logo is displayed in the header.
     * 
     * @return true if logo is displayed, false otherwise
     */
    public boolean isLogoDisplayed() {
        try {
            return logo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Click on the logo to navigate to home page.
     */
    public void clickLogo() {
        logo.click();
    }
    
    /**
     * Check if navigation menu is displayed.
     * 
     * @return true if navigation menu is displayed, false otherwise
     */
    public boolean isNavigationMenuDisplayed() {
        try {
            return navigationMenu.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Click on Sign In link.
     */
    public void clickSignIn() {
        signInLink.click();
    }
    
    /**
     * Click on Sign Up link.
     */
    public void clickSignUp() {
        signUpLink.click();
    }
    
    /**
     * Check if Sign In link is displayed.
     * 
     * @return true if Sign In link is displayed, false otherwise
     */
    public boolean isSignInLinkDisplayed() {
        try {
            return signInLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if Sign Up link is displayed.
     * 
     * @return true if Sign Up link is displayed, false otherwise
     */
    public boolean isSignUpLinkDisplayed() {
        try {
            return signUpLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if user profile is displayed (when user is logged in).
     * 
     * @return true if user profile is displayed, false otherwise
     */
    public boolean isUserProfileDisplayed() {
        try {
            return userProfile.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get the WebDriver instance used by this component.
     * 
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }
}
