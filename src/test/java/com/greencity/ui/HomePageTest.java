package com.greencity.ui;

import com.greencity.ui.pages.newspage.NewsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

public class HomePageTest extends BaseTest{

    @Test(description = "[Case#44]Verify Eco News section display and 'Read all news' button functionality for unregistered user.")
    public void verifyEcoNewsSectionAndNavigation() {
        homePage.scrollToEcoNewsSection();

        WebElement titleElement = homePage.getEcoNewsTitleElement();
        String titleClassName = titleElement.getAttribute("class");
        Assert.assertTrue(titleClassName.contains("section-caption"),
                "Verification failed: Title element does not have expected styling class 'section-caption' for centering.");

        Assert.assertEquals(homePage.getEcoNewsTitleText(), "Eco news",
                "Verification failed: The section title text is incorrect or not visible.");

        WebElement readAllNewsButton = homePage.getReadAllNewsButton();
        // Expected Result 2: The "Read all news" button is present.
        Assert.assertTrue(readAllNewsButton.isDisplayed(),
                "Verification failed: The 'Read all news' button is not displayed.");


        Assert.assertTrue(readAllNewsButton.getText().trim().contains("Read all news"),
                "Verification failed: The 'Read all news' button text is incorrect.");

        NewsPage newsPage = homePage.clickReadAllNewsButton();

        //Ensure that a new page with a list of news articles is opened.
        Assert.assertTrue(driver.getCurrentUrl().contains("/news"),
                "Verification failed: Clicking 'Read all news' did not navigate to the news page.");

        //  Verify that the articles are sorted by publishing date in descending order (newest articles appear first).
        boolean isSortedDescending = newsPage.areArticlesSortedByDateDescending();

        Assert.assertTrue(isSortedDescending,
                "Verification failed: News articles are not sorted by publishing date in descending order (newest first).");


    }
}
