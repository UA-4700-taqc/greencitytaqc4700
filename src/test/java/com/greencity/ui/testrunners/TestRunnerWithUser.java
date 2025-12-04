package com.greencity.ui.testrunners;

import com.greencity.ui.components.auth.SignInModal;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class TestRunnerWithUser extends BaseTestRunner {

    @BeforeClass
    public void login() {
        //ToDo Refactor to use LoginPage object after implementation LoginModal component
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(homePage.getHeader().getHeaderContainer()));

        if (homePage.getHeader().getLanguageSwitcher().getText().equals("En")) {
            homePage.getHeader().clickLanguageOption();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@aria-label='Uk']"))).click();
        }

        SignInModal modal = homePage.getHeader().openSignInModal();

        modal.enterEmail(testValueProvider.getUserEmail());
        modal.enterPassword(testValueProvider.getUserPassword());
        modal.clickSignIn();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("container-user-image")));
    }
}