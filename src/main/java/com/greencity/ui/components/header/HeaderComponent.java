package com.greencity.ui.components.header;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeaderComponent extends BaseComponent {

    @Getter
    @FindBy(className = "header_container")
    private WebElement headerContainer;

    @Getter
    @FindBy(css = ".header_logo img")
    private WebElement headerLogo;

    @Getter
    @FindBy(className = "header_navigation-menu")
    private WebElement navigationMenu;

    @Getter
    @FindBy(className = "header_navigation-menu-left")
    private WebElement navigationMenuLeft;

    @Getter
    @FindBy(className = "header_navigation-menu-right")
    private WebElement navigationMenuRight;

    @Getter
    @FindBy(css = ".nav-left-list a.url-name")
    private List<WebElement> navigationLinks;

    @Getter
    @FindBy(xpath = ".//a[@class='url-name' and contains(@href, '/news')]")
    private WebElement ecoNewsLink;

    @Getter
    @FindBy(xpath = ".//a[@class='url-name' and contains(@href, '/events')]")
    private WebElement eventsLink;

    @Getter
    @FindBy(xpath = ".//a[@class='url-name' and contains(@href, '/places')]")
    private WebElement placesLink;

    @Getter
    @FindBy(xpath = ".//a[@class='url-name' and contains(@href, '/about')]")
    private WebElement aboutUsLink;

    @Getter
    @FindBy(xpath = ".//a[@class='url-name' and contains(@href, '/profile')]")
    private WebElement mySpaceLink;

    @Getter
    @FindBy(xpath = ".//a[@class='url-name' and contains(@href, '/ubs')]")
    private WebElement ubsCourierLink;

    @Getter
    @FindBy(className = "search-icon")
    private WebElement searchIcon;

    @Getter
    @FindBy(className = "header_lang-switcher-wrp")
    private WebElement languageSwitcher;

    @Getter
    @FindBy(className = "lang-option")
    private WebElement languageOption;

    @Getter
    @FindBy(className = "header_sign-in-link")
    private WebElement signInButton;

    @Getter
    @FindBy(className = "header_sign-up-link")
    private WebElement signUpButton;

    @Getter
    @FindBy(className = "header_burger-btn")
    private WebElement burgerMenuButton;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickHeaderLogo() {
        headerLogo.click();
    }

    public void clickSearchIcon() {
        searchIcon.click();
    }

    public void clickLanguageOption() {
        languageOption.click();
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public void clickSignUp() {
        signUpButton.click();
    }

    public void clickBurgerMenu() {
        burgerMenuButton.click();
    }

    public void clickEcoNewsLink() {
        ecoNewsLink.click();
    }

    public void clickEventsLink() {
        eventsLink.click();
    }

    public void clickPlacesLink() {
        placesLink.click();
    }

    public void clickAboutUsLink() {
        aboutUsLink.click();
    }

    public void clickMySpaceLink() {
        mySpaceLink.click();
    }

    public void clickUbsCourierLink() {
        ubsCourierLink.click();
    }
}