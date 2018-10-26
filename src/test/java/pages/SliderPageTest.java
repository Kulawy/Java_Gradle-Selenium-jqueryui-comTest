package pages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.base.BasePageTest;
import pages.base.LeftMenuPage;
import pages.base.RightMenuPage;

import static org.testng.Assert.*;

public class SliderPageTest extends BasePageTest {

    SliderPage sliderPage;

    @BeforeMethod
    public void setUpSlider() {
        _left = new LeftMenuPage(chromeDriver);
        _left.goToSlider();
        _right = new RightMenuPage(chromeDriver);
        _right.goToCustomHandle();
        sliderPage = new SliderPage(chromeDriver, _left, _right);

    }

    @Test
    public void sliderTest(){
        sliderPage.clickMover()
                .sliderStep(80)
                .assertAfterSteps(80)
                .sliderStep(50)
                .assertAfterSteps(50)
                .sliderStep(55)
                .assertAfterSteps(55)
                .sliderStep(55)
                .assertAfterSteps(55);
    }


}