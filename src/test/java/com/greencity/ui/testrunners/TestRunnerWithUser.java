package com.greencity.ui.testrunners;

import com.greencity.ui.pages.homepage.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;

public class TestRunnerWithUser extends BaseTestRunner{

    @BeforeClass
    public void login() {

        By singInLocator = By.xpath("/html/body/app-root/app-main/div/app-header/header/div[2]/div/div/div/ul/a");
        WebElement signInBtn = wait.until(ExpectedConditions.presenceOfElementLocated(singInLocator));
        signInBtn.click();

        By emailInputLocator = By.id("email");
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(emailInputLocator));
        emailInput.clear();
        emailInput.sendKeys(testValueProvider.getUserEmail());

        WebElement passwordInput = this.driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys(testValueProvider.getUserPassword());
        WebElement submitButton = this.driver.findElement(By.xpath("//app-sign-in/div/div[1]/form/button"));
        submitButton.click();


    }
}
