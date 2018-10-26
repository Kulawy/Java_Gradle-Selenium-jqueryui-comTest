package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BasePageTest {

    private static final String _DRIVERPATH= "src/main/resources/chromedriver.exe";
    private static final String _SITEHTTP= "https://jqueryui.com/";
    protected WebDriver chromeDriver;
    protected LeftMenuPage _left;
    protected RightMenuPage _right;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", _DRIVERPATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-extensions");
        chromeDriver = new ChromeDriver(options);
        chromeDriver.get(_SITEHTTP);
    }

    @AfterMethod
    public void tearDown(){
        chromeDriver.quit();
    }

}
