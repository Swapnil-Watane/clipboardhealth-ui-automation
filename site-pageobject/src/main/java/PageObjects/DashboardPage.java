package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends PageObjectBaseClass {

    @FindBy(css = "#gw-desktop-herotator a.a-carousel-goto-prevpage")
    private WebElement heRotatorPreviousButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500)).until(ExpectedConditions.visibilityOf(heRotatorPreviousButton));
    }

    public NavigationZone getNavigationZone(){
        return new NavigationZone(driver);
    }

}
