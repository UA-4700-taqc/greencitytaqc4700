package com.greencity.ui.pages.newspage;

import com.greencity.ui.components.tagfilter.TagFilterComponent;
import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsPage extends BasePage {

    @FindBy(xpath = "//app-tag-filter")
    private WebElement tagFilterRoot;

    @Getter
    private TagFilterComponent tagFilterComponent;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    public TagFilterComponent getTagFilterComponent() {
        if (tagFilterComponent == null) {
            tagFilterComponent = new TagFilterComponent(driver, tagFilterRoot);
        }
        return tagFilterComponent;
    }

    public NewsPage loadNewsPage() {
        waitUntilElementVisible(tagFilterRoot);
        return this;
    }
}
