package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.util.Iterator;
import java.util.List;

public class Select {
    private final WebElement element;

    public Select(WebElement element) {
        String tagName = element.getTagName().toLowerCase();
        if (null != tagName && "select".equals(tagName)){
            this.element = element;
        } else {
            throw new UnexpectedTagNameException("select", tagName);
        }
    }

    public void selectByVisibleText(String text) {
        List<WebElement> options =  this.element.findElements(By.xpath(".//option[normalize-space(.) = " + Quotes.escape(text) + "]"));
        Iterator op = options.iterator();

        while(op.hasNext()) {
            WebElement option = (WebElement) op.next();
            this.setSelected(option);
        }
    }

    private void setSelected(WebElement option) {
        if (option.isSelected() != true) {
            option.click();
        }
    }
}
