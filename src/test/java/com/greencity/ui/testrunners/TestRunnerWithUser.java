package com.greencity.ui.testrunners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;

public class TestRunnerWithUser extends BaseTestRunner {

    @BeforeClass
    public void login() {
        homePage.getHeader().clickSignIn();


        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailInput.clear();
        emailInput.sendKeys(testValueProvider.getUserEmail());

        WebElement passwordInput = this.driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys(testValueProvider.getUserPassword());

        WebElement submitButton = this.driver.findElement(By.xpath("//app-sign-in/div/div[1]/form/button"));
        submitButton.click();
    }
}
