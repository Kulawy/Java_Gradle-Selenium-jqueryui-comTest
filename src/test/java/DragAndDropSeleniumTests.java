import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class DragAndDropSeleniumTests extends BaseSeleniumTests{

    private String dragId;
    private String dropId;

    @BeforeMethod
    public void setUpParametrized(){
        chromeDriver.get("https://jqueryui.com/droppable/");
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        chromeDriver.switchTo().frame(chromeDriver.findElement(By.tagName("iframe")));
        dragId = "draggable";
        dropId = "droppable";
    }

    @Test
    public void dragAndDropTest(){

        WebElement dragWebElement = chromeDriver.findElement(By.id(dragId));
        WebElement dropWebElement = chromeDriver.findElement(By.id(dropId));
        dragAndDropAction(dragWebElement, dropWebElement);
        sleepForTreeSec();

        Assert.assertEquals(dropWebElement.getText(), "Dropped!");

    }

    private void dragAndDropAction(WebElement source, WebElement target){
        Actions actions = new Actions(chromeDriver);
        actions.dragAndDrop(source, target);
        actions.perform();
    }

    private void dragAndDropSecondAction(WebElement source, WebElement target){
        Actions actions = new Actions(chromeDriver);
        actions.clickAndHold(source).moveToElement(target).release(source).build().perform();
    }

    private void sleepForTreeSec(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
