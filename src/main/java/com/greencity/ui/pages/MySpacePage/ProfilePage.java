package com.greencity.ui.pages.MySpacePage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage {

    // Profile header section
    @FindBy(css = ".name")
    private WebElement profileName;

    @FindBy(css = ".rate p")
    private WebElement profileRate;

    @FindBy(css = "a.edit-icon[href*='/edit']")
    private WebElement editProfileIcon;

    // Profile stats
    @FindBy(xpath = "//div[@class='chain'][.//p[text()='acquired habits']]/p[1]")
    private WebElement acquiredHabitsCount;

    @FindBy(xpath = "//div[@class='chain'][.//p[text()='habits in progress']]/p[1]")
    private WebElement habitsInProgressCount;

    @FindBy(xpath = "//div[@class='chain'][.//p[text()='published news']]/p[1]")
    private WebElement publishedNewsCount;

    @FindBy(xpath = "//div[@class='chain'][.//p[text()='organized and attended events']]/p[1]")
    private WebElement eventsCount;

    // Achievements section
    @FindBy(css = ".my-achievements")
    private WebElement achievementsTitle;

    @FindBy(css = ".achieved-quantity")
    private WebElement achievementsCount;

    @FindBy(css = ".no-achievements")
    private WebElement noAchievementsMessage;

    // Friends section
    @FindBy(css = ".text-title p")
    private WebElement friendsTitle;

    @FindBy(css = ".text-number")
    private WebElement friendsCount;

    @FindBy(css = ".add-friends a")
    private WebElement addFriendsButton;

    // Other sections (Eco Places, To-do List)
    @FindBy(css = ".eco-places-content .title")
    private WebElement ecoPlacesTitle;

    @FindBy(css = ".favourites-quantity span")
    private WebElement favouritesCount;

    @FindBy(css = ".to-do-list-content .header")
    private WebElement toDoListTitle;

    @FindBy(css = ".items-count")
    private WebElement toDoListItemsCount;

    // Dashboard Tabs
    @FindBy(xpath = "//div[contains(@class, 'mat-mdc-tab') and .//span[text()='My habits']]")
    private WebElement myHabitsTab;

    @FindBy(xpath = "//div[contains(@class, 'mat-mdc-tab') and .//span[text()='My news']]")
    private WebElement myNewsTab;

    @FindBy(xpath = "//div[contains(@class, 'mat-mdc-tab') and .//span[text()='My Events']]")
    private WebElement myEventsTab;

    @FindBy(css = "#create-button-add-new-habit")
    private WebElement addNewHabitButton;

    @FindBy(css = ".no-data")
    private WebElement noHabitsMessage;

    // Calendar section
    @FindBy(css = ".calendar-week .week")
    private WebElement calendarWeekRange;

    @FindBy(css = ".arrow-previous")
    private WebElement calendarPreviousWeek;

    @FindBy(css = ".arrow-next")
    private WebElement calendarNextWeek;

    // Profile Cards section
    @FindBy(css = ".cart-title")
    private WebElement factOfTheDayTitle;

    @FindBy(css = ".card-description")
    private WebElement factOfTheDayDescription;

    // Constructor
    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Profile info methods
    public String getProfileName() {
        return profileName.getText();
    }

    public String getProfileRate() {
        return profileRate.getText().replace("Rate: ", "").trim();
    }

    public void clickEditProfile() {
        editProfileIcon.click();
    }

    // Stats methods
    private int parseCount(WebElement element) {
        String text = element.getText();
        String numberOnly = text.replaceAll("[^0-9]", "");
        return numberOnly.isEmpty() ? 0 : Integer.parseInt(numberOnly);
    }

    public int getAcquiredHabitsCount() {
        return parseCount(acquiredHabitsCount);
    }

    public int getHabitsInProgressCount() {
        return parseCount(habitsInProgressCount);
    }

    public int getPublishedNewsCount() {
        return parseCount(publishedNewsCount);
    }

    public int getEventsCount() {
        return parseCount(eventsCount);
    }

    // Achievements methods
    public String getAchievementsTitle() {
        return achievementsTitle.getText();
    }

    public int getAchievementsCount() {
        return parseCount(achievementsCount);
    }

    public boolean isNoAchievementsMessageDisplayed() {
        try {
            return noAchievementsMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Friends methods
    public String getFriendsTitle() {
        return friendsTitle.getText();
    }

    public int getFriendsCount() {
        return parseCount(friendsCount);
    }

    public void clickAddFriends() {
        addFriendsButton.click();
    }

    // Tab methods
    public void clickMyHabitsTab() {
        myHabitsTab.click();
    }

    public void clickMyNewsTab() {
        myNewsTab.click();
    }

    public void clickMyEventsTab() {
        myEventsTab.click();
    }

    public void clickAddNewHabit() {
        addNewHabitButton.click();
    }

    public boolean isMyHabitsTabActive() {
        return myHabitsTab.getAttribute("class").contains("mdc-tab--active");
    }

    public boolean isNoHabitsMessageDisplayed() {
        try {
            return noHabitsMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Calendar methods
    public String getCalendarWeekRange() {
        return calendarWeekRange.getText();
    }

    public void clickPreviousWeek() {
        calendarPreviousWeek.click();
    }

    public void clickNextWeek() {
        calendarNextWeek.click();
    }

    // Verification method
    public boolean isProfilePageDisplayed() {
        return profileName.isDisplayed() && myHabitsTab.isDisplayed();
    }
}