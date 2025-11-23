package com.greencity.ui.pages.newspage;

import com.greencity.ui.components.tagfilter.TagFilterComponent;
import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import java.util.List;

public class NewsPage extends BasePage {

    @FindBy(xpath = "//app-tag-filter")
    private WebElement tagFilterRoot;

    @Getter
    private TagFilterComponent tagFilterComponent;

    @FindBy(xpath = "//h1[@class='main-header']")
    private WebElement mainHeader;

    @FindBy(xpath = "//span[@class='search-img']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[@class='bookmark-img']")
    private WebElement bookmarkButton;

    @FindBy(xpath = "//img[@class='my-events-img']")
    private WebElement myEventsButton;

    @FindBy(xpath = "//app-remaining-count//h2")
    private WebElement itemsFoundCounter;

    private final By FIRST_NEWS_ITEM_LOCATOR = By.xpath("//ul[@aria-label='news list']/li");

    @FindBy(xpath = "//span[contains(@class, 'btn-tiles')]")
    private WebElement tilesViewButton;

    @FindBy(xpath = "//span[contains(@class, 'btn-bars')]")
    private WebElement listViewButton;

    @FindBy(xpath = "//ul[@aria-label='news list']")
    private WebElement newsListContainer;

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
        waitUntilElementVisible(driver.findElement(FIRST_NEWS_ITEM_LOCATOR));
        return this;
    }

    public int getItemsFoundCount() {
        waitUntilElementVisible(itemsFoundCounter);
        String text = itemsFoundCounter.getText().trim();

        try {
            String[] parts = text.split("\\s+");
            String countString = parts[0];
            return Integer.parseInt(countString);

        } catch (Exception e) {
            System.err.println("Error extracting or parsing number from items counter text: " + text + ". Error: " + e.getMessage());
            return -1;
        }
    }

    public NewsPage clickTilesViewButton() {
        clickDynamicElement(tilesViewButton);
        return this;
    }

    public NewsPage clickListViewButton() {
        clickDynamicElement(listViewButton);
        return this;
    }

    public List<WebElement> getNewsItems() {
        waitUntilElementVisible(newsListContainer);
        return newsListContainer.findElements(By.xpath("./li"));
    }

    public NewsPage clickSearchButton() {
        clickDynamicElement(searchButton);
        return this;
    }


    public WebElement getMainHeader() {
        return mainHeader;
    }

    public WebElement getTilesViewButton() {
        return tilesViewButton;
    }

    public WebElement getListViewButton() {
        return listViewButton;
    }
}
