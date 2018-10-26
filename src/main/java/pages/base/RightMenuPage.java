package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class RightMenuPage extends BasePage{

    public RightMenuPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(_driver, this);
        List<WebElement> elements;
        switch (_driver.getCurrentUrl()){
            case ("https://jqueryui.com/datepicker/"):
                elements = Arrays.asList(elementDataPickerOtherMonths);
                break;
            case ("https://jqueryui.com/droppable/"):
                elements = Arrays.asList(elementDragAndDropDefault);
                break;
            case ("https://jqueryui.com/slider/"):
                elements = Arrays.asList(elementSliderCustomHandle);
                break;
            default:
                elements = Arrays.asList(elementLogo);
                System.out.println("chyba coś się zje...");
        }
        //PageFactory.initElements(_driver, this);
        //List<WebElement> elements = Arrays.asList();
        waitForElements(elements);
    }

    @FindBy( css = "a[href='/resources/demos/datepicker/other-months.html']")
    private WebElement elementDataPickerOtherMonths;
    @FindBy( css = "a[href='/resources/demos/droppable/default.html']")
    private WebElement elementDragAndDropDefault;
    @FindBy( css = "a[href='/resources/demos/slider/custom-handle.html']")
    private WebElement elementSliderCustomHandle;
    @FindBy( css = ".logo a[title='jQuery UI']")
    private WebElement elementLogo;

    public void goToOtherMonths(){
        clickElement(elementDataPickerOtherMonths);
    }

    public void goToDefault(){
        clickElement(elementDragAndDropDefault);
    }

    public void goToCustomHandle(){
        clickElement(elementSliderCustomHandle);
    }


}
