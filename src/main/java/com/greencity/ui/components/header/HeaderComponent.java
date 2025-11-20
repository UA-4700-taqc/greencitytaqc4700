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

    private void clickElement(WebElement element) {
        waitUntilElementVisible(element);
        waitUntilElementClickable(element);
        element.click();
    }

    public void clickHeaderLogo() {
        clickElement(headerLogo);
    }

    public void clickSearchIcon() {
        clickElement(searchIcon);
    }

    public void clickLanguageOption() {
        clickElement(languageOption);
    }

    public void clickSignIn() {
        clickElement(signInButton);
    }

    public void clickSignUp() {
        clickElement(signUpButton);
    }

    public void clickBurgerMenu() {
        clickElement(burgerMenuButton);
    }

    public void clickNavigationLink(String linkName) {
        WebElement link = getNavigationLinkByName(linkName);
        clickElement(link);
    }

    private WebElement getNavigationLinkByName(String linkName) {
        return switch (linkName.toLowerCase()) {
            case "eco news", "econews" -> ecoNewsLink;
            case "events" -> eventsLink;
            case "places" -> placesLink;
            case "about us", "aboutus" -> aboutUsLink;
            case "my space", "myspace" -> mySpaceLink;
            case "ubs courier", "ubscourier", "ubs" -> ubsCourierLink;
            default -> throw new IllegalArgumentException("Unknown navigation link: " + linkName);
        };
    }
}