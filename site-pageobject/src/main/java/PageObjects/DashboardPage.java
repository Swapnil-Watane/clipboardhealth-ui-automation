package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends PageObjectBaseClass {

    @FindBy(css = "#nav-signin-tooltip a[data-nav-ref='nav_custrec_signin']")
    private WebElement signInPopUp;

    public DashboardPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500)).until(ExpectedConditions.invisibilityOf(signInPopUp));
    }

    public NavigationZone getNavigationZone(){
        return new NavigationZone(driver);
    }

}
