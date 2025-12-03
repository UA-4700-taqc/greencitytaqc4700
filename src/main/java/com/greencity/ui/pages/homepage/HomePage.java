package com.greencity.ui.pages.homepage;

import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.MySpacePage.ProfilePage;
import com.greencity.ui.pages.newspage.NewsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.greencity.ui.components.AdSectionComponent;
import com.greencity.ui.components.header.HeaderComponent;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(xpath = "//button[contains(text(), 'Почати формувати звичку!')]")
    private WebElement startHabitButton;

    @FindBy(xpath = "//header")
    private WebElement headerRoot;

    @FindBy(id = "main-content")
    private WebElement adSectionRoot;

    @FindBy(xpath = "//section[@id='events']")
    private WebElement ecoNewsSection;

    @FindBy(xpath = "//section[@id='events']/h2[@class='section-caption']")
    private WebElement ecoNewsTitle;

    @FindBy(xpath = "//section[@id='events']//a[contains(text(), 'Read all news')]")
    private WebElement readAllNewsButton;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(headerRoot));
    }

    public HeaderComponent getHeader() {
        return new HeaderComponent(driver, headerRoot);
    }
    public ProfilePage clickStartHabitButton() {
        waitUntilElementClickable(startHabitButton);
        startHabitButton.click();
        return new ProfilePage(driver);
    }

    public HomePage scrollToEcoNewsSection() {
        scrollIntoView(ecoNewsSection);
        return this;
    }
    public AdSectionComponent getAdSectionComponent() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement freshRootElement = explicitWait.until(ExpectedConditions.visibilityOf(adSectionRoot));
        return new AdSectionComponent(driver, freshRootElement);
    }

    public String getEcoNewsTitleText() {
        waitUntilElementVisible(ecoNewsTitle);
        return ecoNewsTitle.getText();
    }

    public NewsPage clickReadAllNewsButton() {
        clickDynamicElement(readAllNewsButton);
        return new NewsPage(driver).loadNewsPage();
    }

    public WebElement getEcoNewsTitleElement() {
        waitUntilElementVisible(ecoNewsTitle);
        return ecoNewsTitle;
    }

    public WebElement getReadAllNewsButton() {
        waitUntilElementVisible(readAllNewsButton);
        return readAllNewsButton;
    }
}