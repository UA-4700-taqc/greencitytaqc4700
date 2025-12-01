package com.greencity.ui.components.tagfilter;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.tagfilter.enums.TagFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TagFilterComponent extends BaseComponent {

    @FindBy(xpath = ".//span[@class='filter']")
    private WebElement filterByTitle;

    @FindBy(xpath = ".//div[@class='ul-eco-buttons']/button")
    private List<WebElement> tagButtons;

    public TagFilterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getFilterByTitle() {
        return filterByTitle;
    }

    public List<WebElement> getTagButtons() {
        return tagButtons;
    }

    public void clickTagByName(TagFilter tagFilter) {

        String tagName = tagFilter.getTagName();

        for (WebElement button : tagButtons) {
            WebElement tagTextElement = button.findElement(By.xpath(".//span[@class='text']"));
            if (tagTextElement.getText().equalsIgnoreCase(tagName)) {
                waitUntilElementClickable(button);
                button.click();
                return;
            }
        }
    }
}
