# GreenCity TAQC 4700 - Test Automation Project

This is a test automation project for the GreenCity application, built using the Page Object Model (POM) pattern.

## Project Structure

```
greencitytaqc4700/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── greencity/
│   │               └── components/
│   │                   └── HeaderComponent.java
│   └── test/
│       └── java/
│           └── com/
│               └── greencity/
│                   └── tests/
│                       └── HeaderComponentTest.java
├── pom.xml
├── testng.xml
└── README.md
```

## Technologies Used

- **Java 11**: Programming language
- **Maven**: Build and dependency management
- **Selenium WebDriver 4.15.0**: Browser automation
- **TestNG 7.8.0**: Testing framework
- **WebDriverManager 5.6.2**: Automatic WebDriver management
- **Mockito 5.7.0**: Mocking framework for unit tests

## Components

### HeaderComponent

The `HeaderComponent` class represents the header section of the GreenCity application. It follows the Page Object Model pattern and encapsulates:

- **Elements**:
  - Header container
  - Logo
  - Navigation menu
  - Sign In link
  - Sign Up link
  - User profile

- **Methods**:
  - `isHeaderDisplayed()`: Check if header is visible
  - `isLogoDisplayed()`: Check if logo is visible
  - `clickLogo()`: Click on the logo
  - `isNavigationMenuDisplayed()`: Check if navigation menu is visible
  - `clickSignIn()`: Click on Sign In link
  - `clickSignUp()`: Click on Sign Up link
  - `isSignInLinkDisplayed()`: Check if Sign In link is visible
  - `isSignUpLinkDisplayed()`: Check if Sign Up link is visible
  - `isUserProfileDisplayed()`: Check if user profile is visible (when logged in)
  - `getDriver()`: Get the WebDriver instance

## Building the Project

```bash
mvn clean compile
```

## Running Tests

Run all tests:
```bash
mvn test
```

Run specific test suite:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Test Reports

After running tests, reports can be found in:
- `target/surefire-reports/` - Maven Surefire reports
- `test-output/` - TestNG reports

## Usage Example

```java
import com.greencity.components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Example {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://greencity-app-url.com");
        
        HeaderComponent header = new HeaderComponent(driver);
        
        if (header.isHeaderDisplayed()) {
            header.clickLogo();
        }
        
        driver.quit();
    }
}
```

## Contributing

When adding new page components, follow the Page Object Model pattern:
1. Create a new class in `src/main/java/com/greencity/components/`
2. Use `@FindBy` annotations to locate elements
3. Initialize with `PageFactory.initElements(driver, this)` in the constructor
4. Create methods for user actions and element checks
5. Add corresponding test class in `src/test/java/com/greencity/tests/`

## License

This project is for educational purposes as part of TAQC 4700 course.
