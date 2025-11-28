package com.greencity.ui.components.header;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class HeaderSearchComponent extends BaseComponent {

    @Getter
    @FindBy(className = "search_bar-wrapper")
    private WebElement searchBarWrapper;

    @Getter
    @FindBy(className = "search_bar-container")
    private WebElement searchBarContainer;

    @Getter
    @FindBy(className = "search_search-icon")
    private WebElement searchIcon;

    @Getter
    @FindBy(className = "search_search-field")
    private WebElement searchField;

    @Getter
    @FindBy(className = "search_close-icon")
    private WebElement closeIcon;

    @Getter
    @FindBy(className = "search_body-wrapper")
    private WebElement searchBodyWrapper;

    @Getter
    @FindBy(className = "search-content-wrapper")
    private WebElement searchContentWrapper;

    public HeaderSearchComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    /**
     * Waits for the search bar to close completely.
     * Uses a shorter timeout for faster test execution.
     */
//    public void waitForSearchBarToClose() {
//        getWait(3).until(ExpectedConditions.invisibilityOf(searchBarWrapper));
//    }
    public void waitForSearchBarToClose() {
        waitUntilElementInvisibleSafe(searchBarWrapper);
    }
    public HeaderSearchComponent waitForSearchBarToOpen() {
        waitUntilElementVisible(searchBarWrapper);
        return this;
    }


    public void enterSearchQuery(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Search query cannot be null or empty");
        }
        waitUntilElementVisible(searchField);
        searchField.clear();
        searchField.sendKeys(query);
    }

    public void submitSearch() {
        waitUntilElementClickable(searchField);
        searchField.sendKeys(Keys.ENTER);
    }

    public void searchFor(String query) {
        enterSearchQuery(query);
        submitSearch();
    }

    public void clearSearchField() {
        waitUntilElementVisible(searchField);
        searchField.clear();
    }

    public void clickCloseIcon() {
        waitUntilElementClickable(closeIcon);
        closeIcon.click();
        waitForSearchBarToClose();
    }

    public String getSearchFieldPlaceholder() {
        waitUntilElementVisible(searchField);
        return searchField.getAttribute("placeholder");
    }

    public String getSearchFieldValue() {
        waitUntilElementVisible(searchField);
        return searchField.getAttribute("value");
    }

    /**
     * Checks if search bar is displayed without long waits.
     * Returns immediately based on current state.
     */
    public boolean isSearchBarDisplayed() {
        return searchBarWrapper.isDisplayed();
    }

    public boolean isSearchFieldEmpty() {
        String value = getSearchFieldValue();
        return value == null || value.isEmpty();
    }

    /**
     * Checks if search bar is closed without long waits.
     * Returns immediately based on current state.
     */
    public boolean isSearchBarClosed() {
        return !searchBarWrapper.isDisplayed();
    }

    /**
     * Checks if search content is visible without long waits.
     * Returns immediately based on current state.
     */
    public boolean isSearchContentVisible() {
        return searchContentWrapper.isDisplayed();
    }
}