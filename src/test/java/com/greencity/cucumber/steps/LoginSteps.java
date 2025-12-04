package com.greencity.cucumber.steps;

import com.greencity.ui.components.auth.SignInModal;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSteps {

    private final Hooks hooks;
    private HomePage homePage;

    public LoginSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    @Given("the user is logged into the system")
    public void given_the_user_is_logged_into_system() {
        WebDriverWait wait = new WebDriverWait(hooks.getDriver(), java.time.Duration.ofSeconds(20));
        homePage = new HomePage(hooks.getDriver());
        wait.until(ExpectedConditions.visibilityOf(homePage.getHeader().getHeaderContainer()));

        if (homePage.getHeader().getLanguageSwitcher().getText().equals("En")) {
            homePage.getHeader().clickLanguageOption();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@aria-label='Uk']"))).click();
        }

        SignInModal modal = homePage.getHeader().openSignInModal();

        modal.enterEmail(hooks.getTestValueProvider().getUserEmail());
        modal.enterPassword(hooks.getTestValueProvider().getUserPassword());
        modal.clickSignIn();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("container-user-image")));
    }


}
