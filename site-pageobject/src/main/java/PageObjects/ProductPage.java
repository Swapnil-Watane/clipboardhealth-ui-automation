package PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends PageObjectBaseClass   {
    @FindBy(css = "#main-image-container")
    private WebElement imageConatiner;

    @FindBy(css = "#feature-bullets")
    private WebElement aboutThisSection;

    @FindBy(css = "#feature-bullets h1")
    private WebElement aboutThisIteamHeader;

    @FindBy(css = "#feature-bullets ul")
    private WebElement getAboutThisIteamBody;

    protected ProductPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500)).until(ExpectedConditions.visibilityOf(imageConatiner));
    }

    public Boolean isAboutThisItemSectionPresent(){
        try {
            return aboutThisSection.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getAboutThisItemSection() {
        return aboutThisSection.getText();
    }

}
