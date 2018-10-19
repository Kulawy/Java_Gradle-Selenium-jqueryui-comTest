
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SliderSeleniumTests extends BaseSeleniumTests{


    @BeforeMethod
    public void setUpSliderTest(){
        chromeDriver.get("https://jqueryui.com/slider/#custom-handle");
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        chromeDriver.switchTo().frame(chromeDriver.findElement(By.tagName("iframe")));
        //WebDriverWait wait = new WebDriverWait(chromeDriver, 15);
    }

    @Test
    public void sliderFirstTest(){

        String sliderId = "slider";
        String moverId = "custom-handle";

        WebElement sliderBarElement = chromeDriver.findElement(By.id(sliderId));
        //WebElement sliderBarElement = chromeDriver.findElement(By.xpath("//div[@id='slider']"));
        Assert.assertTrue(sliderBarElement.isDisplayed());
        Dimension sliderSize = sliderBarElement.getSize();
        int sliderWidth = sliderSize.getWidth();

        int xCoord = sliderBarElement.getLocation().getX();

        Actions builder = new Actions(chromeDriver);
        builder.moveToElement(sliderBarElement)
                .click()
                .dragAndDropBy(sliderBarElement,xCoord + sliderWidth, 0)
                .build()
                .perform();

        WebElement sliderMoverElement = chromeDriver.findElement(By.id(moverId));
        Assert.assertTrue(sliderBarElement.isDisplayed());

        //int moverValue = Integer.parseInt(sliderBarElement.getAttribute("value"));
        //int moverValue = Integer.parseInt(sliderMoverElement.getAttribute("value"));

        //Assert.assertEquals(moverValue, 1000000);

        String sliderPercent = sliderMoverElement.getAttribute("style");
        //Assert.assertTrue(sliderPercent.contains("left: 100"));
        Assert.assertEquals(sliderPercent, "left: 50%;");

    }

    @Test
    public void sliderSecondTest(){
        String sliderXpath = "//div[@id='slider']";
        String moverId = "custom-handle";

        // sliderBarElement = chromeDriver.findElement(By.id(sliderId));
        WebElement sliderBarElement = chromeDriver.findElement(By.xpath(sliderXpath));
        Assert.assertTrue(sliderBarElement.isDisplayed());
        Dimension sliderSize = sliderBarElement.getSize();
        int sliderWidth = sliderSize.getWidth();

        int xCoord = sliderBarElement.getLocation().getX();
        System.out.println("bar location X value: " + xCoord);
        System.out.println("slider width " + sliderWidth);

        sliderBarElement.click();

        WebElement sliderMoverElement = chromeDriver.findElement(By.id(moverId));
        moveJQuerySliderByClickAndHold(sliderMoverElement, sliderWidth/2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveJQuerySliderLeftByArrowKey(1);
        //moveJQuerySliderByClickAndHold(sliderMoverElement, sliderWidth/15);

        Assert.assertTrue(sliderBarElement.isDisplayed());

        String sliderPercent = sliderMoverElement.getAttribute("style");
        //Assert.assertTrue(sliderPercent.contains("left: 100"));
        Assert.assertEquals(sliderPercent, "left: 49%;");
    }

    @Test
    public void sliderWorstTest(){
        String moverId = "custom-handle";
        WebElement sliderMoverElement = chromeDriver.findElement(By.id(moverId));
        sliderMoverElement.click();

        moveJQuerySliderRightByArrowKey(80);
        assertAfterSteps(sliderMoverElement, 80);
        sleepForOneSec();
        moveJQuerySliderLeftByArrowKey(30);
        assertAfterSteps(sliderMoverElement, 50);
        sleepForOneSec();
        moveJQuerySliderRightByArrowKey(5);
        assertAfterSteps(sliderMoverElement, 55);
        sleepForOneSec();
        moveJQuerySliderLeftByArrowKey(0);
        assertAfterSteps(sliderMoverElement, 55);
        sleepForOneSec();
    }


    @Test
    public void sliderMainTest(){
        String moverId = "custom-handle";
        WebElement sliderMoverElement = chromeDriver.findElement(By.id(moverId));
        sliderMoverElement.click();

        moveJQuerySliderByArrowKey(sliderMoverElement, 80);
        assertAfterSteps(sliderMoverElement, 80);
        sleepForOneSec();
        moveJQuerySliderByArrowKey(sliderMoverElement, 50);
        assertAfterSteps(sliderMoverElement, 50);
        sleepForOneSec();
        moveJQuerySliderByArrowKey(sliderMoverElement, 55);
        assertAfterSteps(sliderMoverElement, 55);
        sleepForOneSec();
        moveJQuerySliderByArrowKey(sliderMoverElement, 55);
        assertAfterSteps(sliderMoverElement, 55);
        sleepForOneSec();


    }

    private void assertAfterSteps(WebElement sliderMover, int value){
        String testingString = "left: " + value + "%;";
        String sliderPercent = sliderMover.getAttribute("style");
        Assert.assertEquals(sliderPercent, testingString);
    }

    private void moveJQuerySliderByClickAndHold(WebElement widget, int offset){
        Actions actions = new Actions(chromeDriver);
        Action action = actions.clickAndHold(widget).moveByOffset(offset, 0).release().build();
        action.perform();

    }

    private void moveJQuerySliderByArrowKey(WebElement elem, int target){
        int actualPos = Integer.parseInt(elem.getText());
        System.out.println(actualPos);
        int step;
        if ( actualPos > target){
            step = actualPos-target;
            moveJQuerySliderLeftByArrowKey(step);
        }
        else {
            step = target-actualPos;
            moveJQuerySliderRightByArrowKey(target-actualPos);
        }
    }

    private void moveJQuerySliderLeftByArrowKey(int step){
        for(int i=0; i < step; i++){
            Actions actions = new Actions(chromeDriver);
            Action action = actions.sendKeys(Keys.ARROW_LEFT).build();
            action.perform();
        }

    }

    private void moveJQuerySliderRightByArrowKey(int step){
        for(int i=0; i < step; i++) {
            Actions actions = new Actions(chromeDriver);
            Action action = actions.sendKeys(Keys.ARROW_RIGHT).build();
            action.perform();
        }
    }

    private void sleepForOneSec(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    @Test
    public void moveSliderToEnd() {

        driver.get(url);

        WebElement priceSlider =
                findElement(priceSliderLocator);

        assertTrue(priceSlider.isDisplayed());

        Dimension sliderSize = priceSlider.getSize();
        int sliderWidth = sliderSize.getWidth();

        int xCoord = priceSlider.getLocation().getX();

        Actions builder = new Actions(driver);
        builder.moveToElement(priceSlider)
                .click()
                .dragAndDropBy
                        (priceSlider,xCoord + sliderWidth, 0)
                .build()
                .perform();

        WebElement hiddenPrice =
                findHiddenElement(hiddenPriceLocator);

        int priceValue = Integer.parseInt(
                hiddenPrice.getAttribute("value"));

        assertEquals(priceValue, 1000000);

        priceSlider = findElement(priceSliderLocator);

        String sliderPercent =
                priceSlider.getAttribute("style");

        assertTrue(sliderPercent.contains("left: 100"));
    }
    */


}
