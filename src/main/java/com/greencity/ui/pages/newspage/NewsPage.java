package com.greencity.ui.pages.newspage;

import com.greencity.ui.components.tagfilter.TagFilterComponent;
import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    @FindBy(xpath = "//a[contains(@class , \"create \")]")
    private WebElement createNewsButton;

    private final By NEWS_ITEM_DATE_LOCATOR = By.xpath(".//p[contains(@class, 'user-data-text-date')]//span");

    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

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

    public List<LocalDate> getArticleDates() {
        List<LocalDate> dates = new ArrayList<>();
        List<WebElement> articles = getNewsItems();

        for (WebElement article : articles) {
            try {
                WebElement dateElement = article.findElement(NEWS_ITEM_DATE_LOCATOR);
                String dateText = dateElement.getText().trim();
                LocalDate date = LocalDate.parse(dateText, DATE_FORMATTER);
                dates.add(date);
            } catch (Exception e) {
                System.err.println("Could not parse date for an article. Error: " + e.getMessage());
            }
        }
        return dates;
    }

    public boolean areArticlesSortedByDateDescending() {
        List<LocalDate> dates = getArticleDates();
        if (dates.size() <= 1) {
            return true;
        }
        for (int i = 0; i < dates.size() - 1; i++) {
            if (dates.get(i).isBefore(dates.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    public NewsPage clickSearchButton() {
        clickDynamicElement(searchButton);
        return this;
    }

    public  void clickCreateNewsButton() {
        clickDynamicElement(createNewsButton);
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
