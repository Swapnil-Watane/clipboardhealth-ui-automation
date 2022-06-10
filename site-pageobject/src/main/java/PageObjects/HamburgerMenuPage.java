package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HamburgerMenuPage extends PageObjectBaseClass{

    @FindBy(css = "#hmenu-content")
    private WebElement hamburgerMenu;

    @FindBy(css = "#hmenu-content ul.hmenu-visible li")
    private List<WebElement> hamburgeMenuItemList;

    public HamburgerMenuPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500)).until(ExpectedConditions.visibilityOf(hamburgerMenu));
    }

    public HamburgerMenuPage selectMenuAndWaitToSelectSubMenu(String menuItemText) {
        HamburgerMenuContainer container = new HamburgerMenuContainer(driver, hamburgeMenuItemList);
        WebElement element = container.getSelectedElement(menuItemText);
        return click(element, HamburgerMenuPage.class);
    }

    public BrowseCategoryPage selectSubMenuAndSeeResults(String menuItemText) {
        HamburgerMenuContainer container = new HamburgerMenuContainer(driver, hamburgeMenuItemList);
        WebElement element = container.getSelectedElement(menuItemText);
        return click(element, BrowseCategoryPage.class);
    }

    private static final class HamburgerMenuContainer  extends ContainerBaseClass{
        private By menuLink = By.cssSelector("a.hmenu-item");

        public HamburgerMenuContainer(WebDriver driver, List<WebElement> list) {
            super(driver, list);
        }

        public WebElement getSelectedElement(String itemText) {
            return this.getElementBasedOn(elementWithMatchingText(menuLink,itemText));
        }
    }
}
