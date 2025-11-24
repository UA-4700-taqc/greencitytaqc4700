package com.greencity.ui.components.createnews;

import com.greencity.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MetaSection extends BaseComponent {

    @FindBy(xpath = ".//div[@class='date']/p[1]/span[2]")
    private WebElement creationDate;

    @FindBy(xpath = ".//div[@class='date']/p[2]/span[2]")
    private WebElement authorName;

    public MetaSection(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
    }

    public String getCreationDate() {
        return creationDate.getText().trim();
    }

    public String getAuthorName() {
        return authorName.getText().trim();
    }
}
