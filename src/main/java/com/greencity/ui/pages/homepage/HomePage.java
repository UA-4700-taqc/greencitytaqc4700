package com.greencity.ui.pages.homepage;

import com.greencity.ui.components.header.HeaderComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.newspage.NewsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//section[@id='events']")
    private WebElement ecoNewsSection;

    @FindBy(xpath = "//section[@id='events']/h2[@class='section-caption']")
    private WebElement ecoNewsTitle;

    @FindBy(xpath = "//section[@id='events']//a[contains(text(), 'Read all news')]")
    private WebElement readAllNewsButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage scrollToEcoNewsSection() {
        scrollIntoView(ecoNewsSection);
        return this;
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