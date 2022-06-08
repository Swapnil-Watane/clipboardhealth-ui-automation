package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationZone extends PageObjectBaseClass {

    @FindBy(css = "#nav-hamburger-menu")
    private WebElement hamburgerMenuButton;

    public NavigationZone(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500)).until(ExpectedConditions.visibilityOf(hamburgerMenuButton));
    }

    public HamburgerMenuPage goToHamburgerMenu(){
        return click(hamburgerMenuButton, HamburgerMenuPage.class);
    }



}
