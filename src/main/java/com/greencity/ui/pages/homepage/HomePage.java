package com.greencity.ui.pages.homepage;

import com.greencity.ui.components.header.HeaderComponent;
import com.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(xpath = "//header")
    private WebElement headerRoot;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HeaderComponent getHeader() {
        return new HeaderComponent(driver, headerRoot);
    }
}