package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class LeftMenuPage extends BasePage{

    public LeftMenuPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(_driver, this);
        List<WebElement> elements = Arrays.asList(elementDatePicker, elementDragAndDrop, elementSlider);
        waitForElements(elements);
    }

    @FindBy( css = "a[href='https://jqueryui.com/datepicker/']")
    private WebElement elementDatePicker;

    @FindBy( css = "a[href='https://jqueryui.com/droppable/']")
    private WebElement elementDragAndDrop;

    @FindBy( css = "a[href='https://jqueryui.com/slider/']")
    private WebElement elementSlider;

    public void goToDatePicker(){
        clickElement(elementDatePicker);
    }

    public void goToDragAndDrop(){
        clickElement(elementDragAndDrop);
    }

    public void goToSlider(){
        clickElement(elementSlider);
    }


}
