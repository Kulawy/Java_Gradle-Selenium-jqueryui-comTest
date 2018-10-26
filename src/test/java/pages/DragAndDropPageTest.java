package pages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.base.BasePageTest;
import pages.base.LeftMenuPage;
import pages.base.RightMenuPage;

import static org.testng.Assert.*;

public class DragAndDropPageTest extends BasePageTest {

    DragAndDropPage dragAndDropPage;

    @BeforeMethod
    public void setUpDragAndDrop(){
        _left = new LeftMenuPage(chromeDriver);
        _left.goToDragAndDrop();
        _right = new RightMenuPage(chromeDriver);
        _right.goToDefault();
        dragAndDropPage = new DragAndDropPage(chromeDriver, _left, _right);
    }

    @Test
    public void dragAndDropTest(){
        dragAndDropPage.dragAndDropAction().VeryfiSucces();
    }

}