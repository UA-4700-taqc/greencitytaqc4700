package com.greencity.ui;

import com.greencity.ui.components.header.HeaderComponent;
import com.greencity.ui.components.header.HeaderSearchComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class HeaderSearchComponentTest extends BaseTestRunner {

    private static final int PAGE_LOAD_TIMEOUT = 5; // Reduced from 10
    private static final String EXPECTED_PLACEHOLDER = "Search";
    private static final String TEST_SEARCH_QUERY = "eco friendly";
    private static final String SEARCH_ROOT_TAG = "app-search-popup";
    private HeaderSearchComponent searchComponent;

    @BeforeMethod
    public void openSearchPanel() {
        System.out.println("🔍 Opening search panel...");
        homePage.waitForPageToLoad(PAGE_LOAD_TIMEOUT);
        HeaderComponent header = homePage.getHeader();
        header.clickSearchIcon();

        // Wait for search panel to appear with shorter timeout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchRoot = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.tagName(SEARCH_ROOT_TAG))
        );

        searchComponent = new HeaderSearchComponent(driver, searchRoot);
        System.out.println("✅ Search panel opened");
    }

    @AfterMethod
    public void closeSearchPanelIfOpen() {
        System.out.println("🧹 Cleaning up search panel...");
        if (searchComponent != null && searchComponent.isSearchBarDisplayed()) {
            searchComponent.clickCloseIcon();
            searchComponent.waitForSearchBarToClose();
            System.out.println("✅ Search panel closed");
        }
    }

    @Test(description = "Verify search bar wrapper is displayed")
    public void testSearchBarWrapperIsDisplayed() {
        System.out.println("🧪 Testing: Search bar wrapper visibility");
        WebElement searchBarWrapper = searchComponent.getSearchBarWrapper();
        Assert.assertTrue(searchBarWrapper.isDisplayed(), "Search bar wrapper should be displayed");
        System.out.println("✅ Test passed");
    }

    @Test(description = "Verify search bar container is displayed")
    public void testSearchBarContainerIsDisplayed() {
        System.out.println("🧪 Testing: Search bar container visibility");
        WebElement searchBarContainer = searchComponent.getSearchBarContainer();
        Assert.assertTrue(searchBarContainer.isDisplayed(), "Search bar container should be displayed");
        System.out.println("✅ Test passed");
    }

    @Test(description = "Verify search icon is displayed")
    public void testSearchIconIsDisplayed() {
        System.out.println("🧪 Testing: Search icon visibility");
        WebElement searchIcon = searchComponent.getSearchIcon();
        Assert.assertTrue(searchIcon.isDisplayed(), "Search icon should be displayed");
        System.out.println("✅ Test passed");
    }

    @Test(description = "Verify search field is displayed")
    public void testSearchFieldIsDisplayed() {
        System.out.println("🧪 Testing: Search field visibility");
        WebElement searchField = searchComponent.getSearchField();
        Assert.assertTrue(searchField.isDisplayed(), "Search field should be displayed");
        System.out.println("✅ Test passed");
    }

    @Test(description = "Verify close icon is displayed")
    public void testCloseIconIsDisplayed() {
        System.out.println("🧪 Testing: Close icon visibility");
        WebElement closeIcon = searchComponent.getCloseIcon();
        Assert.assertTrue(closeIcon.isDisplayed(), "Close icon should be displayed");
        System.out.println("✅ Test passed");
    }

    @Test(description = "Verify search body wrapper is displayed")
    public void testSearchBodyWrapperIsDisplayed() {
        System.out.println("🧪 Testing: Search body wrapper visibility");
        WebElement searchBodyWrapper = searchComponent.getSearchBodyWrapper();
        Assert.assertTrue(searchBodyWrapper.isDisplayed(), "Search body wrapper should be displayed");
        System.out.println("✅ Test passed");
    }

    @Test(description = "Verify search content wrapper is displayed")
    public void testSearchContentWrapperIsDisplayed() {
        System.out.println("🧪 Testing: Search content wrapper visibility");
        WebElement searchContentWrapper = searchComponent.getSearchContentWrapper();
        Assert.assertTrue(searchContentWrapper.isDisplayed(), "Search content wrapper should be displayed");
        System.out.println("✅ Test passed");
    }

    @Test(description = "Verify search field placeholder text")
    public void testSearchFieldPlaceholder() {
        System.out.println("🧪 Testing: Search field placeholder");
        String placeholder = searchComponent.getSearchFieldPlaceholder();
        Assert.assertEquals(placeholder, EXPECTED_PLACEHOLDER,
                "Search field placeholder should be '" + EXPECTED_PLACEHOLDER + "'");
        System.out.println("✅ Test passed - Placeholder: " + placeholder);
    }

    @Test(description = "Verify search field is initially empty")
    public void testSearchFieldIsEmpty() {
        System.out.println("🧪 Testing: Search field is empty");
        Assert.assertTrue(searchComponent.isSearchFieldEmpty(), "Search field should be empty initially");
        System.out.println("✅ Test passed");
    }

    @Test(description = "Verify enter search query functionality")
    public void testEnterSearchQuery() {
        System.out.println("🧪 Testing: Enter search query - '" + TEST_SEARCH_QUERY + "'");
        searchComponent.enterSearchQuery(TEST_SEARCH_QUERY);
        String actualValue = searchComponent.getSearchFieldValue();
        Assert.assertEquals(actualValue, TEST_SEARCH_QUERY,
                "Search field should contain '" + TEST_SEARCH_QUERY + "'");
        System.out.println("✅ Test passed - Query entered: " + actualValue);
    }

    @Test(description = "Verify clear search field functionality")
    public void testClearSearchField() {
        System.out.println("🧪 Testing: Clear search field");
        searchComponent.enterSearchQuery(TEST_SEARCH_QUERY);
        System.out.println("   Entered: " + TEST_SEARCH_QUERY);

        searchComponent.clearSearchField();
        System.out.println("   Cleared field");

        Assert.assertTrue(searchComponent.isSearchFieldEmpty(), "Search field should be empty after clear");
        System.out.println("✅ Test passed");
    }

    @Test(description = "Verify close icon click functionality")
    public void testCloseIconClick() {
        System.out.println("🧪 Testing: Close icon click");
        searchComponent.clickCloseIcon();
        Assert.assertTrue(searchComponent.isSearchBarClosed(),
                "Search bar should be closed after clicking close icon");
        System.out.println("✅ Test passed");
    }

    @Test(description = "Verify search submission functionality")
    public void testSearchSubmission() {
        System.out.println("🧪 Testing: Search submission");
        searchComponent.searchFor(TEST_SEARCH_QUERY);
        System.out.println("✅ Test passed - Search submitted for: " + TEST_SEARCH_QUERY);
    }

    @Test(description = "Verify all search elements are displayed")
    public void testAllSearchElementsAreDisplayed() {
        System.out.println("🧪 Testing: All search elements visibility");
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
        System.out.println("✅ Test passed - All elements visible");
    }

    @Test(description = "Verify search content is visible after entering query")
    public void testSearchContentVisibility() {
        System.out.println("🧪 Testing: Search content visibility after query");
        searchComponent.enterSearchQuery(TEST_SEARCH_QUERY);

        // Add small wait for content to appear
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        boolean isContentVisible = searchComponent.isSearchContentVisible();
        Assert.assertTrue(isContentVisible, "Search content wrapper should be visible after entering query");
        System.out.println("✅ Test passed - Content visible");
    }
}