package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.base.BasePage;
import pages.base.LeftMenuPage;
import pages.base.RightMenuPage;

import java.util.Arrays;
import java.util.List;

public class SliderPage extends BasePage {


    public SliderPage(WebDriver driver, LeftMenuPage left, RightMenuPage right){
        super(driver);
        _left = left;
        _right = right;
        PageFactory.initElements(_driver, this);
        _actionsBuilder = new Actions(_driver);
        _driver.switchTo().frame(elementIFrame);
        List<WebElement> elements = Arrays.asList(elementMover, elementSlider);
        waitForElements(elements);
    }

    @FindBy( id = "custom-handle")
    private WebElement elementMover;
    @FindBy( xpath = "//div[@id='slider']")
    private WebElement elementSlider;
    @FindBy( tagName = "iframe")
    private WebElement elementIFrame;

    public SliderPage clickMover(){
        clickElement(elementMover);
        return this;
    }

    public SliderPage sliderStep(int target){
        int actualPos = Integer.parseInt(elementMover.getText());
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
        return this;
    }

    public SliderPage assertAfterSteps(int value){
        String testingString = "left: " + value + "%;";
        String sliderPercent = elementMover.getAttribute("style");
        Assert.assertEquals(sliderPercent, testingString);
        return this;
    }

    private void moveJQuerySliderLeftByArrowKey(int step){
        for(int i=0; i < step; i++){
            Action action = _actionsBuilder.sendKeys(Keys.ARROW_LEFT).build();
            action.perform();
        }

    }

    private void moveJQuerySliderRightByArrowKey(int step){
        for(int i=0; i < step; i++) {
            Action action = _actionsBuilder.sendKeys(Keys.ARROW_RIGHT).build();
            action.perform();
        }
    }




}
