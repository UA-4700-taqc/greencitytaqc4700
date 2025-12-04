package com.greencity.ui;

import com.greencity.ui.testrunners.BaseTestRunner;
import com.greencity.ui.components.auth.SignInModal;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdSectionTestNoAuth extends BaseTestRunner {

    @Test(description = "Перевірка, що CTA-кнопки для неавторизованих користувачів відкривають модальне вікно входу/реєстрації")
    @Step("Test CTA buttons open Sign In modal for unregistered user")
    public void testUnregisteredUserCtaOpensAuthModal() {
        SoftAssert softAssert = new SoftAssert();

        SignInModal modalEcoBag = homePage.clickCtaEcoBag();

        softAssert.assertTrue(modalEcoBag.isModalVisible(),
                "Модальне вікно авторизації НЕ відкрилося після кліку на CTA Eco Bag.");

        softAssert.assertFalse(driver.getCurrentUrl().contains(testValueProvider.getBaseUIUrl()),
                "Користувач був перенаправлений на іншу сторінку.");

        modalEcoBag.close();


        // 2. Тест для секції Cups
        SignInModal modalCups = homePage.clickCtaCups();

        softAssert.assertTrue(modalCups.isModalVisible(),
                "Модальне вікно авторизації НЕ відкрилося після кліку на CTA Cups.");

        softAssert.assertFalse(driver.getCurrentUrl().contains(testValueProvider.getBaseUIUrl()),
                "Користувач був перенаправлений на іншу сторінку.");

        softAssert.assertAll();
    }


}
