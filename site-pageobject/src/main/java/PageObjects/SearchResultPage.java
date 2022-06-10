package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultPage extends PageObjectBaseClass{

    @FindBy(css = "#search div.s-desktop-content")
    private WebElement resultContent;

    @FindBy(css = "div [data-component-type='s-search-result']")
    private List<WebElement> resultList;

    @FindBy(css = "#s-result-sort-select")
    private WebElement selectList;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500)).until(ExpectedConditions.visibilityOf(resultContent));
    }

    public SearchResultPage sortBasedOn(String value) {
        Select selectLst = new Select(selectList);
        selectLst.selectByVisibleText(value);
        return this;
    }

    public void selectResultBasedOnItemLocation(int location) {
        SearchResultContainer container = new SearchResultContainer(driver, resultList);
        WebElement element = container.getSubElementOfParentElementAtIndex(location-1);
        click(element);
    }

    private static final class SearchResultContainer extends ContainerBaseClass {
        private By resultLink = By.cssSelector("h2 a.a-link-normal");

        protected SearchResultContainer(WebDriver driver, List<WebElement> parentElement) {
            super(driver, parentElement);
        }

        public WebElement getSubElementOfParentElementAtIndex(int index) {
            WebElement element = this.getParentElementWithIndex(index);
            return element.findElement(resultLink);
        }
    }
}
