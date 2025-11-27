package com.greencity.ui;

import com.greencity.ui.components.header.HeaderComponent;
import com.greencity.ui.components.header.HeaderSearchComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HeaderSearchComponentTest extends BaseTestRunner {

    private static final int PAGE_LOAD_TIMEOUTS = 10;
    private static final String EXPECTED_PLACEHOLDER = "Search";
    private static final String TEST_SEARCH_QUERY = "eco friendly";
    private static final String SEARCH_ROOT_TAG = "app-search-popup";
    private HeaderSearchComponent searchComponent;

    @BeforeMethod
    public void openSearchPanel() {
        homePage.waitForPageToLoad(PAGE_LOAD_TIMEOUTS);
        HeaderComponent header = homePage.getHeader();
        header.clickSearchIcon();
        WebElement searchRoot = driver.findElement(By.tagName(SEARCH_ROOT_TAG));
        searchComponent = new HeaderSearchComponent(driver, searchRoot);
    }

    @AfterMethod
    public void closeSearchPanelIfOpen() {
        if (searchComponent != null && searchComponent.isSearchBarDisplayed()) {
            searchComponent.clickCloseIcon();
            // Wait for the panel to close completely
            searchComponent.waitForSearchBarToClose();
        }
    }

    @Test(description = "Verify search bar wrapper is displayed")
    public void testSearchBarWrapperIsDisplayed() {
        WebElement searchBarWrapper = searchComponent.getSearchBarWrapper();
        Assert.assertTrue(searchBarWrapper.isDisplayed(), "Search bar wrapper should be displayed");
    }

    @Test(description = "Verify search bar container is displayed")
    public void testSearchBarContainerIsDisplayed() {
        WebElement searchBarContainer = searchComponent.getSearchBarContainer();
        Assert.assertTrue(searchBarContainer.isDisplayed(), "Search bar container should be displayed");
    }

    @Test(description = "Verify search icon is displayed")
    public void testSearchIconIsDisplayed() {
        WebElement searchIcon = searchComponent.getSearchIcon();
        Assert.assertTrue(searchIcon.isDisplayed(), "Search icon should be displayed");
    }

    @Test(description = "Verify search field is displayed")
    public void testSearchFieldIsDisplayed() {
        WebElement searchField = searchComponent.getSearchField();
        Assert.assertTrue(searchField.isDisplayed(), "Search field should be displayed");
    }

    @Test(description = "Verify close icon is displayed")
    public void testCloseIconIsDisplayed() {
        WebElement closeIcon = searchComponent.getCloseIcon();
        Assert.assertTrue(closeIcon.isDisplayed(), "Close icon should be displayed");
    }

    @Test(description = "Verify search body wrapper is displayed")
    public void testSearchBodyWrapperIsDisplayed() {
        WebElement searchBodyWrapper = searchComponent.getSearchBodyWrapper();
        Assert.assertTrue(searchBodyWrapper.isDisplayed(), "Search body wrapper should be displayed");
    }

    @Test(description = "Verify search content wrapper is displayed")
    public void testSearchContentWrapperIsDisplayed() {
        WebElement searchContentWrapper = searchComponent.getSearchContentWrapper();
        Assert.assertTrue(searchContentWrapper.isDisplayed(), "Search content wrapper should be displayed");
    }

    @Test(description = "Verify search field placeholder text")
    public void testSearchFieldPlaceholder() {
        String placeholder = searchComponent.getSearchFieldPlaceholder();
        Assert.assertEquals(placeholder, EXPECTED_PLACEHOLDER, 
            "Search field placeholder should be '" + EXPECTED_PLACEHOLDER + "'");
    }

    @Test(description = "Verify search field is initially empty")
    public void testSearchFieldIsEmpty() {
        Assert.assertTrue(searchComponent.isSearchFieldEmpty(), "Search field should be empty initially");
    }

    @Test(description = "Verify enter search query functionality")
    public void testEnterSearchQuery() {
        searchComponent.enterSearchQuery(TEST_SEARCH_QUERY);
        String actualValue = searchComponent.getSearchFieldValue();
        Assert.assertEquals(actualValue, TEST_SEARCH_QUERY, 
            "Search field should contain '" + TEST_SEARCH_QUERY + "'");
    }

    @Test(description = "Verify clear search field functionality")
    public void testClearSearchField() {
        searchComponent.enterSearchQuery(TEST_SEARCH_QUERY);
        searchComponent.clearSearchField();
        Assert.assertTrue(searchComponent.isSearchFieldEmpty(), "Search field should be empty after clear");
    }

    @Test(description = "Verify close icon click functionality")
    public void testCloseIconClick() {
        searchComponent.clickCloseIcon();
        Assert.assertTrue(searchComponent.isSearchBarClosed(), "Search bar should be closed after clicking close icon");
    }

    @Test(description = "Verify search submission functionality")
    public void testSearchSubmission() {
        searchComponent.searchFor(TEST_SEARCH_QUERY);
    }

    @Test(description = "Verify all search elements are displayed")
    public void testAllSearchElementsAreDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchComponent.getSearchBarWrapper().isDisplayed(), 
            "Search bar wrapper should be displayed");
        softAssert.assertTrue(searchComponent.getSearchBarContainer().isDisplayed(), 
            "Search bar container should be displayed");
        softAssert.assertTrue(searchComponent.getSearchIcon().isDisplayed(), 
            "Search icon should be displayed");
        softAssert.assertTrue(searchComponent.getSearchField().isDisplayed(), 
            "Search field should be displayed");
        softAssert.assertTrue(searchComponent.getCloseIcon().isDisplayed(), 
            "Close icon should be displayed");
        softAssert.assertAll();
    }

    @Test(description = "Verify search content is visible after entering query")
    public void testSearchContentVisibility() {
        searchComponent.enterSearchQuery(TEST_SEARCH_QUERY);
        boolean isContentVisible = searchComponent.isSearchContentVisible();
        Assert.assertTrue(isContentVisible, "Search content wrapper should be visible after entering query");
    }
}
