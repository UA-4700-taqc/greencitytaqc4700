package com.greencity.ui;

import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import com.greencity.ui.pages.CreateEcoNewsItemPage;

    public class CreateEcoNewsItemPageTest extends BaseTestRunner {

        private WebDriver driver;
        private CreateEcoNewsItemPage createNewsPage;

        @BeforeMethod
        public void openPageBeforeEachTest() {
            createNewsPage = new CreateEcoNewsItemPage(driver);
            createNewsPage.visitPage();
        }


        @Test
        public void testHeaderSection() {
            String expectedTitleHeader = "Створити новину";
            String expectedTitleDescription = "Будь ласка, надайте якомога більше деталей - час та місце події, мета зборів тощо. Ви можете повернутись та оновити новину в будь-який час після публікації.";

            String actualTitleHeader = createNewsPage.header.getHeader();
            String actualTitleDescription = createNewsPage.header.getDescription();

            Assert.assertEquals(actualTitleHeader, expectedTitleHeader, "Title of the page doesn't match the expected text");
            Assert.assertEquals(actualTitleDescription, expectedTitleDescription, "Description of the page doesn't match the expected text");
        }

        @Test
        public void testTagSection() {

        }

        @Test
        public void testImageSection() {

        }

        @Test
        public void testContentSection() {

        }

        @Test
        public void testMetaSection() {

        }

        @Test
        public void testActionButtonsSection() {

        }
    }
