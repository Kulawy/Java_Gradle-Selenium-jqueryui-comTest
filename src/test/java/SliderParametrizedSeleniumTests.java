import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class SliderParametrizedSeleniumTests extends BaseSeleniumTests {

    private String sliderXpath;
    private String moverId;

    @BeforeMethod
    public void setUpParametrized(){
        chromeDriver.get("https://jqueryui.com/slider/#custom-handle");
        chromeDriver.switchTo().frame(chromeDriver.findElement(By.tagName("iframe")));
        sliderXpath = "//div[@id='slider']";
        moverId = "custom-handle";
    }

    @DataProvider(name="sliderTask")
    public Object[][] createDataForSlider(){
        return new Object[][]{
                {80},
                {50},
                {55},
                {55}
        };
    }

    //@Parameters({"valueOne"})
    @Test(dataProvider = "sliderTask")
    public void sliderParameterTest(int step){

        WebElement sliderBarElement = chromeDriver.findElement(By.xpath(sliderXpath));
        Assert.assertTrue(sliderBarElement.isDisplayed());
        Dimension sliderSize = sliderBarElement.getSize();
        int sliderWidth = sliderSize.getWidth();


        WebElement sliderMoverElement = chromeDriver.findElement(By.id(moverId));
        sliderMoverElement.click();
        //moveJQuerySliderByClickAndHold(sliderMoverElement, sliderWidth/2);
        //int val = Integer.parseInt(step);
        System.out.println(step);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i<step; i++){
            moveJQuerySliderRightByArrowKey();
            System.out.println(i);

        }

        String testingString = "left: " + step + "%;";
        String sliderPercent = sliderMoverElement.getAttribute("style");
        Assert.assertEquals(sliderPercent, testingString);
    }

    private void moveJQuerySliderByClickAndHold(WebElement widget, int offset){
        Actions actions = new Actions(chromeDriver);
        Action action = actions.clickAndHold(widget).moveByOffset(offset, 0).release().build();
        action.perform();

    }

    private void moveJQuerySliderLeftByArrowKey(){
        Actions actions = new Actions(chromeDriver);
        Action action = actions.sendKeys(Keys.ARROW_LEFT).build();
        action.perform();
    }

    private void moveJQuerySliderRightByArrowKey(){
        Actions actions = new Actions(chromeDriver);
        Action action = actions.sendKeys(Keys.ARROW_RIGHT).build();
        action.perform();
    }

}
