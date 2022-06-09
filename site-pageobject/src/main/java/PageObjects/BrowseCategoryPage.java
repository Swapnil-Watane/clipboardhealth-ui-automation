package PageObjects;

import one.util.streamex.StreamEx;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

public class BrowseCategoryPage extends PageObjectBaseClass  {

    @FindBy(css = "#a-page div.a-container")
    private WebElement browseContainer;

    @FindBy(css = "#s-refinements div.a-spacing-none")
    private List<WebElement> refinementCategoryList;

    private By checkList = By.cssSelector("ul li.a-spacing-micro");

    private By filterCheckBox = By.cssSelector("span");

    protected BrowseCategoryPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500)).until(ExpectedConditions.visibilityOf(browseContainer));
    }


    public ResultPage refineBasedOn(String category, String filter){
        RefinementContainer categoryContainer = new RefinementContainer(driver, refinementCategoryList);
        int index = categoryContainer.getIndexOfElement(category);
        WebElement element = categoryContainer.getElement(index);
        scrollIntoView(element);
        RefinementContainer filterContainer = new RefinementContainer(driver, element.findElements(checkList));
        WebElement filterCheck = filterContainer.getSelectedElement(filter).findElement(filterCheckBox);
        return click(filterCheck, ResultPage.class);
    }

    private static final class RefinementContainer extends PageObjectBaseClass{
        private List<WebElement> parentElement;
        private By categoryNameBy = By.cssSelector("div.a-spacing-small span");
        private By checkBoxNameBy = By.cssSelector("a.a-link-normal");


        public RefinementContainer(WebDriver driver, List<WebElement> list) {
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

        private int getIndexOfElementBasedOn(Predicate<WebElement> criteria) {
            return (int) StreamEx.of(this.parentElement).indexOf(criteria).orElse(-1L);
        }

        public int getIndexOfElement(String categoryName){
            return this.getIndexOfElementBasedOn(elementWithMatchingText(categoryNameBy, categoryName));
        }

        public WebElement getElement(int index) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Index out of bounds:" + index);
            }
            return (WebElement) this.parentElement.get(index);
        }

        private WebElement getElementBasedOn(Predicate<WebElement> criteria) {
            return (WebElement) this.parentElement.stream().filter(criteria).findFirst().orElse((WebElement) null);
        }

        public WebElement getSelectedElement(String itemText) {
            return this.getElementBasedOn(elementWithMatchingText(checkBoxNameBy, itemText));
        }
    }
}
