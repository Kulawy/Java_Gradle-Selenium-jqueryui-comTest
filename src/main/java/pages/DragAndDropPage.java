package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.base.BasePage;
import pages.base.LeftMenuPage;
import pages.base.RightMenuPage;

import java.util.Arrays;
import java.util.List;

public class DragAndDropPage extends BasePage {

    public DragAndDropPage(WebDriver driver, LeftMenuPage left, RightMenuPage right){
        super(driver);
        _left = left;
        _right = right;
        PageFactory.initElements(_driver, this);
        _driver.switchTo().frame(elementIFrame);
        _actionsBuilder = new Actions(_driver);
        List<WebElement> elements = Arrays.asList(elementDrag, elementDrop);
        waitForElements(elements);
    }

    @FindBy( id = "draggable")
    private WebElement elementDrag;
    @FindBy( id = "droppable")
    private WebElement elementDrop;
    @FindBy( tagName = "iframe")
    private WebElement elementIFrame;

    public DragAndDropPage dragAndDropAction(){
        _actionsBuilder.dragAndDrop(elementDrag, elementDrop).perform();
        return this;
    }

    public DragAndDropPage dragAndDropAction(WebElement source, WebElement target){
        _actionsBuilder.dragAndDrop(source, target);
        _actionsBuilder.perform();
        return this;
    }

    public DragAndDropPage VeryfiSucces(){
        Assert.assertEquals(elementDrop.getText(), "Dropped!");
        return this;
    }


}
