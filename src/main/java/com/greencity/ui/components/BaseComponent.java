package com.greencity.ui.components;

import com.greencity.ui.Base;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.time.Duration;

public abstract class BaseComponent extends Base {

    @Getter
    protected WebElement rootElement;

    public BaseComponent(WebDriver driver, WebElement rootElement) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
        this.rootElement = rootElement;
    }

    @Override
    public boolean isVisibleElementLocated(By selector) {
        Duration originalWait = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(NO_WAIT_TIME));

        try {
            return !rootElement.findElements(selector).isEmpty();
        } finally {
            driver.manage().timeouts().implicitlyWait(originalWait);
        }
    }
}