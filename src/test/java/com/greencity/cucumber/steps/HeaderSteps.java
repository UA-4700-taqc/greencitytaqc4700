package com.greencity.cucumber.steps;

import com.greencity.ui.components.auth.SignInModal;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderSteps {

    private Hooks hooks;

    public HeaderSteps(Hooks hooks) {
       this.hooks = hooks;


    }
    @Given("the user clicks 'Eco news' in the header")
    public void given_the_user_clicks_eco_news_in_header() {
        WebDriverWait wait = new WebDriverWait(hooks.getDriver(), java.time.Duration.ofSeconds(20));
        HomePage homePage = new HomePage(hooks.getDriver());
        wait.until(ExpectedConditions.visibilityOf(homePage.getHeader().getHeaderContainer()));

        homePage.getHeader().clickEcoNewsLink();
    }
}
