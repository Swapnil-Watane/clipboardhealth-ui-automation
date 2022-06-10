package PageObjects;

import one.util.streamex.StreamEx;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Predicate;

public class ContainerBaseClass extends PageObjectBaseClass {
    protected List<WebElement> parentElement;

    protected ContainerBaseClass(WebDriver driver, List<WebElement> parentElement) {
        super(driver);
        this.parentElement = parentElement;
    }

    protected static Predicate<WebElement> elementWithMatchingText(By by, String expectedText) {
        return (we) -> {
            return we.findElements(by).stream().map(WebElement::getText).map(String::trim).anyMatch((actualText) -> {
                return actualText.equals(expectedText);
            });
        };
    }

    protected int getIndexOfElementBasedOn(Predicate<WebElement> criteria) {
        return (int) StreamEx.of(this.parentElement).indexOf(criteria).orElse(-1L);
    }

    protected WebElement getElementBasedOn(Predicate<WebElement> criteria) {
        return (WebElement) this.parentElement.stream().filter(criteria).findFirst().orElse((WebElement) null);
    }

    protected WebElement getParentElementWithIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds:" + index);
        }
        return (WebElement) this.parentElement.get(index);
    }
}
