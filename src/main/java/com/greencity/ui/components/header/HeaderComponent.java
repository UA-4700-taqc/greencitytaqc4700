package com.greencity.ui.components.header;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.auth.SignInModal;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    @FindBy(css = "a.header_sign-in-link")
    private WebElement signInButtonText;

    @Getter
    @FindBy(css = "[alt='sing in button']")
    private WebElement signInButtonIcon;

    //@Getter
    //@FindBy(className = "header_sign-in-link")
    //private WebElement signInButtonLink;

    @Getter
    @FindBy(className = "header_sign-up-link")
    private WebElement signUpButton;

    @Getter
    @FindBy(className = "header_burger-btn")
    private WebElement burgerMenuButton;

    private final String SEARCH_ROOT_TAG = "app-search-popup";

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    private void clickElement(WebElement element) {
        waitUntilElementClickable(element);
        element.click();
    }

    public void clickEcoNewsLink() {
        clickElement(ecoNewsLink);
    }


    public void clickHeaderLogo() {
        clickElement(headerLogo);
    }

    public HeaderSearchComponent clickSearchIcon() {
        waitUntilElementClickable(searchIcon);
        try {
            searchIcon.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // Fallback to JavaScript click if element is intercepted
            clickDynamicElement(searchIcon);
        }
        WebElement searchRoot = getWait(5).until(
                ExpectedConditions.presenceOfElementLocated(By.tagName(SEARCH_ROOT_TAG))
        );
        return new HeaderSearchComponent(driver, searchRoot);
    }

    public void clickLanguageOption() {
        clickElement(languageOption);
    }

    public void clickSignIn() {
        if(signInButtonIcon.isDisplayed()){
            clickElement(signInButtonIcon);
            return;
        }
        if(signInButtonText.isDisplayed()){
            clickElement(signInButtonText);
        }
    }

    public void clickSignUp() {
        clickElement(signUpButton);
    }

    public SignInModal openSignInModal() {
        clickSignIn();
        WebElement modalRoot = getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("app-auth-modal"))
        );
        return new SignInModal(driver, modalRoot);
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


    public HeaderComponent changeLanguageToUk() {
        if (languageSwitcher.getText().equals("En")) {
            clickLanguageOption();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@aria-label='Uk']"))).click();
        }
        return this;
    }
}