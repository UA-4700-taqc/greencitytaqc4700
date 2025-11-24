package com.greencity.ui.components.createnews;

import com.greencity.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderSection extends BaseComponent {

    @FindBy(xpath = ".//h2")
    private WebElement titleHeader;

    @FindBy(xpath = ".//p[@class='title-description']")
    private WebElement titleDescription;

    public HeaderSection(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getHeader() {
        return titleHeader.getText().trim();
    }

    public String getDescription() { return titleDescription.getText().trim(); }
}
