package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

public class HamburgerMenuPage extends PageObjectBaseClass{

    @FindBy(css = "#hmenu-content")
    private WebElement hamburgerMenu;

    // #hmenu-content ul li a.hmenu-item
    @FindBy(css = "#hmenu-content ul li")
    private List<WebElement> hamburgeMenuItemList;

    public HamburgerMenuPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500)).until(ExpectedConditions.visibilityOf(hamburgerMenu));
    }

    public HamburgerMenuPage hamburgerMenuSelect(String menuItemText) {
        HamburgerMenuContainer container = new HamburgerMenuContainer(driver, hamburgeMenuItemList);
        WebElement element = container.getSelectedElement(menuItemText);
        return click(element, HamburgerMenuPage.class);
    }


    private static final class HamburgerMenuContainer  extends PageObjectBaseClass{
        private List<WebElement> parentElement;
        private By menuLink = By.cssSelector("a.hmenu-item");

        public HamburgerMenuContainer(WebDriver driver, List<WebElement> list) {
            super(driver);
            this.parentElement = list;
        }

        private static Predicate<WebElement> elementWithMatchingText(By by, String expectedText) {
            return (we) -> {
                return we.findElements(by).stream().map(WebElement::getText).map(String::trim).anyMatch((actualText) -> {
                    return actualText.equals(expectedText);
                });
            };
        }

        public WebElement getSelectedElement(String itemText) {
            return this.getElementBasedOn(elementWithMatchingText(menuLink,itemText));
        }

        private WebElement getElementBasedOn(Predicate<WebElement> criteria) {
            return (WebElement) this.parentElement.stream().filter(criteria).findFirst().orElse((WebElement) null);
        }

    }
}
