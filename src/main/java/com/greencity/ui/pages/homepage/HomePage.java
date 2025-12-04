package com.greencity.ui.pages.homepage;

import com.greencity.ui.components.auth.SignInModal;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.newspage.NewsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(xpath = "//section[@id='events']")
    private WebElement ecoNewsSection;

    @FindBy(xpath = "//section[@id='events']/h2[@class='section-caption']")
    private WebElement ecoNewsTitle;

    @FindBy(xpath = "//section[@id='events']//a[contains(text(), 'Read all news')]")
    private WebElement readAllNewsButton;

    @FindBy(xpath = "//app-stat-row[contains(., 'пакетів')]//button[normalize-space(text())='Почати формувати звичку!']")
    private WebElement ctaEcoBagButton;

    @FindBy(xpath = "//app-stat-row[contains(., 'склянок')]//button[normalize-space(text())='Почати формувати звичку!']")
    private WebElement ctaCupsButton;

    @FindBy(id = "stats")
    private WebElement statsSection;

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

    public HomePage refresh() {
        refreshPage();
        return new HomePage(driver);
    }

    public SignInModal clickCtaEcoBag() {
        scrollToStatsSection();
        getWait(10).until(ExpectedConditions.elementToBeClickable(ctaEcoBagButton));
        ctaEcoBagButton.click();

        // 3. Динамічний пошук кореневого елемента модального вікна
        By modalBy = By.cssSelector(SignInModal.MODAL_ROOT_CSS);
        WebElement modalRoot = getWait(10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(modalBy));

        return new SignInModal(driver, modalRoot);
    }

    public SignInModal clickCtaCups() {
        scrollToEcoNewsSection();
        ctaCupsButton.click();
        By modalBy = By.cssSelector(SignInModal.MODAL_ROOT_CSS);
        WebElement modalRoot = getWait(10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(modalBy));

        return new SignInModal(driver, modalRoot);
    }

    public HomePage scrollToStatsSection() {
        getWait(10).until(ExpectedConditions.visibilityOf(statsSection));
        scrollIntoView(statsSection);
        return this;
    }


    public HomePage clickCtaEcoBagButton() {
        scrollToStatsSection();
        getWait(10).until(ExpectedConditions.elementToBeClickable(ctaEcoBagButton));
        ctaEcoBagButton.click();
        return this;
    }

    public HomePage clickCtaCupsButton() {
        scrollToStatsSection();
        getWait(10).until(ExpectedConditions.elementToBeClickable(ctaCupsButton));

        ctaCupsButton.click();
        return this;
    }
}