package com.greencity.ui.components.createnews;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MetaSection extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//p[1]/span[2]")
    private WebElement creationDate;

    @Getter
    @FindBy(xpath = ".//p[2]/span[2]")
    private WebElement authorName;

    public MetaSection(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getDate() {
        return creationDate.getText().trim();
    }

    public String getName() {
        return authorName.getText().trim();
    }
}
