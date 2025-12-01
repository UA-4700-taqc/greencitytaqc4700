package com.greencity.ui;

import com.greencity.ui.components.tagfilter.enums.TagFilter;
import com.greencity.ui.pages.newspage.NewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewsPageTest extends BaseTestRunner {


    @Test(description = "Verification of correct news filtering by tag 'Events'")
    public void verifyFilterByEventsTag() {

        homePage.getHeader().clickEcoNewsLink();

        NewsPage newsPage = new NewsPage(driver).loadNewsPage();

        int initialNewsItemsCount = newsPage.getItemsFoundCount();
        Assert.assertTrue(initialNewsItemsCount > 0, "Initial items count is 0, cannot proceed with filtering test.");
        newsPage.getTagFilterComponent().clickTagByName(TagFilter.EVENTS);
        newsPage.loadNewsPage();
        int filteredNewsItemsCount = newsPage.getItemsFoundCount();

        Assert.assertNotEquals(
                initialNewsItemsCount,
                filteredNewsItemsCount,
                "The number of news items did not change after filtering by tag Events."
        );

    }
}

