package com.greencity.ui.testrunners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import static java.lang.Thread.sleep;

public class TestRunnerWithUser extends BaseTestRunner {

    @BeforeClass
    public void login() {
        //ToDo Refactor to use LoginPage object after implementation LoginModal component
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(500));


        if (homePage.getHeader().getLanguageSwitcher().getText().equals("En")) {
            homePage.getHeader().clickLanguageOption();
            WebElement en = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@aria-label='Uk']")));
            en.click();
        }
        homePage.getHeader().clickSignIn();



        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailInput.clear();
        emailInput.sendKeys(testValueProvider.getUserEmail());

        WebElement passwordInput = this.driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys(testValueProvider.getUserPassword());

        WebElement submitButton = this.driver.findElement(By.xpath("//app-sign-in/div/div[1]/form/button"));
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("container-user-image")));
    }
}
