package com.greencity.cucumber.steps;

import com.greencity.ui.pages.CreateEcoNewsItemPage;
import com.greencity.ui.pages.newspage.NewsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NewsPageSteps {
    private final Hooks hooks;
    private NewsPage newsPage;

    public NewsPageSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    @Then("the Eco News page is loaded")
    public void theEcoNewsPageIsLoaded() {
        newsPage = new NewsPage(hooks.getDriver());
        newsPage.loadNewsPage();
        newsPage.waitForPageToLoad(50);
    }

    @When("the user clicks 'Create news'")
    public void theUserClicksCreateNews() {
        newsPage.clickCreateNewsButton();
    }


}
