package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {
    protected WebDriver _driver;
    protected LeftMenuPage _left;
    protected RightMenuPage _right;
    protected Actions _actionsBuilder;

    public BasePage(WebDriver driver){
        _driver = driver;
    }

    public void waitWhile(WebElement elementToWait, int timeWaiting){

    }

    public WebElement clickElement(WebElement element){
        waitUntilClickable(element);
        element.click();
        return element;
    }

    public void waitUntilClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(_driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElements(List<WebElement> elements){
        WebDriverWait wait = new WebDriverWait(_driver,10);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(_driver,10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean areElementsVisible(List<WebElement> elements){
        if ( ExpectedConditions.visibilityOfAllElements(elements) != null ){
            return true;
        }
        else
            return false;
    }

    //public void waitUntilElementsWillVisible(List<WebElement> elements){
    //  waitForElements(elements);
    //}


}
