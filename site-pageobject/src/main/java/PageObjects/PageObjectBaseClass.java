package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.Objects;

public abstract class PageObjectBaseClass {
    protected final WebDriver driver;
    private static final int DefaultWaitForPage = 12;

    protected PageObjectBaseClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected WebElement click(WebElement currentElement, WebElement expectedElement){
        currentElement.click();
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500)).until(ExpectedConditions.visibilityOf(expectedElement));
        return expectedElement;
    }

    protected <W extends PageObjectBaseClass> W click(WebElement element, Class<W> nextPageClass) {
        element.click();
        return waitForNextPage(driver, nextPageClass, DefaultWaitForPage);
    }

    protected static <W extends PageObjectBaseClass> W waitForNextPage(WebDriver driver, Class<W> targetPageObjectClass, int timeOutInSeconds) {
        return (W) new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds),Duration.ofMillis(500)).withMessage("Time out waiting for next page: " + targetPageObjectClass.getName()).until(pageLoaded(targetPageObjectClass));
    }

    public static <X extends PageObjectBaseClass> ExpectedCondition<X> pageLoaded(Class<X> targetPageObjectClass) {
        return (driverObject) -> {
            Objects.requireNonNull(driverObject);

            try {
                X pageObject = (X) targetPageObjectClass.getDeclaredConstructor(WebDriver.class).newInstance(driverObject);
                if ("complete".equals(((JavascriptExecutor)driverObject).executeScript("return document.readyState", new Object[0]))) {
                    return pageObject;
                }
            } catch (NoSuchMethodException var3) {
                throw new RuntimeException(var3);
            } catch (InvocationTargetException var4) {
                if (var4.getCause() instanceof IllegalArgumentException) {
                    throw (IllegalArgumentException)var4.getCause();
                }
            } catch (Exception var5) {
            }

            return null;
        };
    }
}
