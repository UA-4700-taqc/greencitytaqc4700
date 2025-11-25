package com.greencity.ui.components;

import com.greencity.ui.pages.EcoNewsItemPage;
import com.greencity.ui.utils.NewsTag;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class EcoNewsItemComponent extends BaseComponent {

    @FindBy(xpath = ".//div[@role='list']/div/span[not(contains(@class,'tag-divider'))]")
    private List<WebElement> tags;

    @FindBy(css = "div.title-list h3")
    private WebElement title;

    @FindBy(css = "div.list-text div")
    private WebElement text;

    @FindBy(css = "div.user-data-added-news span.mw")
    private WebElement author;

    public EcoNewsItemComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<NewsTag> getTags() {
        return tags.stream()
                .map(WebElement::getText)
                .map(NewsTag::getTagFromText)
                .collect(Collectors.toList());
    }

    public String getTitle() {
        return title.getText().trim();
    }

    public String getText() {
        return text.getText().trim();
    }

    public String getAuthor() {
        return author.getText().trim();
    }

    public EcoNewsItemPage click() {
        title.click();
        return new EcoNewsItemPage(driver);
    }
}
